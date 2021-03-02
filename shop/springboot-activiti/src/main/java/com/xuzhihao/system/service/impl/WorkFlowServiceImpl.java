package com.xuzhihao.system.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xuzhihao.common.api.DataGridView;
import com.xuzhihao.common.api.ResultCode;
import com.xuzhihao.system.model.vo.DeploymentEntityVo;
import com.xuzhihao.system.model.vo.ModelEntityVo;
import com.xuzhihao.system.model.vo.WorkFlowVo;
import com.xuzhihao.system.service.IWorkFlowService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Classname： WorkFlowServiceImpl @Description： @Author： xiedong @Date：
 * 2019/11/6 16:29 @Version： 1.0
 **/
@Service
@Slf4j
public class WorkFlowServiceImpl implements IWorkFlowService {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 查询流程模型信息
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public DataGridView queryProcessModel(WorkFlowVo vo) {
		int firstResult = (vo.getPage() - 1) * vo.getLimit();
		int maxResult = vo.getLimit();
		List<Model> queryResult = new ArrayList<>();
		if (vo.getModelId() != null && !vo.getModelId().equals("")) {
			queryResult = this.repositoryService.createModelQuery().modelId(vo.getModelId())
					.modelNameLike("%" + vo.getModelName() + "%").listPage(firstResult, maxResult);
		}
		// 根据模型名称查询
		if (vo.getModelId() == null || vo.getModelId().equals("")) {
			if (vo.getModelName() == null) {
				vo.setModelName("");
			}
			queryResult = this.repositoryService.createModelQuery().modelNameLike("%" + vo.getModelName() + "%")
					.listPage(firstResult, maxResult);
		}
		long count = queryResult.size();
		ArrayList<ModelEntityVo> objects = new ArrayList<>();

		for (Model model : queryResult) {
			objects.add(new ModelEntityVo(model));
		}
		return new DataGridView(count, objects);
	}

	/**
	 * 在线设计模型
	 *
	 * @return
	 */
	@Override
	public DataGridView onlineModel() {
		String id = "";
		try {
			// 初始化一个空模型
			Model model = this.repositoryService.newModel();

			// 设置一些默认信息
			String name = "新建模型";
			String description = "";
			int revision = 1;
			String key = "process";

			ObjectNode modelNode = objectMapper.createObjectNode();
			modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

			model.setName(name);
			model.setKey(key);
			model.setMetaInfo(modelNode.toString());

			this.repositoryService.saveModel(model);
			id = model.getId();

			// 完善ModelEditorSource
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			this.repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return new DataGridView();
		}
		return new DataGridView(id);
	}

	/**
	 * 删除流程模型
	 *
	 * @param id
	 */
	@Override
	public void deleteProcessModelById(Integer id) {
		this.repositoryService.deleteModel(id + "");
	}

	/**
	 * 发布模型为流程定义
	 *
	 * @param id
	 */
	@Override
	public ResultCode deployModel(String id) {
		try {
			// 获取模型
			Model modelData = this.repositoryService.getModel(id);
			byte[] bytes = this.repositoryService.getModelEditorSource(modelData.getId());

			if (bytes == null) {
				return ResultCode.DEPLOY_ERROR;
			}

			JsonNode modelNode = new ObjectMapper().readTree(bytes);

			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			if (model.getProcesses().size() == 0) {
				return ResultCode.DEPLOY_ERROR;
			}
			byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			// 发布流程
			String processName = modelData.getName() + ".bpmn20.xml";
			Deployment deployment = this.repositoryService.createDeployment().name(modelData.getName())
					.addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
			modelData.setDeploymentId(deployment.getId());
			this.repositoryService.saveModel(modelData);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("【{}】流程部署出现问题", id, e);
			return ResultCode.DEPLOY_ERROR;
		}
		return ResultCode.DEPLOY_SUCCESS;
	}

	/**
	 * 查询流程部署信息
	 */
	@Override
	public DataGridView queryProcessDeploy(WorkFlowVo vo) {
		int firstResult = (vo.getPage() - 1) * vo.getLimit();
		int maxResult = vo.getLimit();
		List<Deployment> queryResult = new ArrayList<>();
		if (vo.getDeploymentId() != null && !vo.getDeploymentId().equals("")) {
			queryResult = this.repositoryService.createDeploymentQuery().deploymentId(vo.getDeploymentId())
					.deploymentNameLike("%" + vo.getDeploymentName() + "%").listPage(firstResult, maxResult);
		}

		if (vo.getDeploymentId() == null || vo.getDeploymentId().equals("")) {
			if (vo.getDeploymentName() == null) {
				vo.setDeploymentName("");
			}
			queryResult = this.repositoryService.createDeploymentQuery()
					.deploymentNameLike("%" + vo.getDeploymentName() + "%").listPage(firstResult, maxResult);
		}
		// 查询总条数
		long size = queryResult.size();
		ArrayList<DeploymentEntityVo> data = new ArrayList<>();
		for (Deployment deployment : queryResult) {
			data.add(new DeploymentEntityVo(deployment));
		}
		return new DataGridView(size, data);
	}

	/**
	 * 根据部署id删除模型
	 *
	 * @param id
	 */
	@Override
	public void deleteProcessDeployById(Integer id) {
		this.repositoryService.deleteDeployment(id + "", true);
	}

	/**
	 * 查看流程图
	 *
	 * @param deploymentId
	 * @return
	 */
	@Override
	public InputStream viewProcessImage(String deploymentId) {
		List<String> names = this.repositoryService.getDeploymentResourceNames(deploymentId);
		for (String resourceName : names) {
			System.out.println(resourceName);
			if (resourceName.endsWith(".png")) {
				InputStream stream = repositoryService.getResourceAsStream(deploymentId, resourceName);
				return stream;
			}
		}
		return null;
	}
}
