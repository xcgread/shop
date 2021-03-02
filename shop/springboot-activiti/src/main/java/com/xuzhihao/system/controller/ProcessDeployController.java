package com.xuzhihao.system.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.common.api.DataGridView;
import com.xuzhihao.common.api.ResultCode;
import com.xuzhihao.system.model.vo.WorkFlowVo;
import com.xuzhihao.system.service.IWorkFlowService;

/**
 * 流程部署
 */
@RequestMapping("prodeploy")
@Controller
public class ProcessDeployController {
	@Autowired
	private IWorkFlowService workFlowService;

	/**
	 * 跳转到流程部署管理界面
	 */
	@RequestMapping("toWorkFlowDeploy")
	public String toWorkFlowDeploy() {
		return "workflow/process-deploylist";
	}

	/**
	 * 查看流程图
	 */
	@RequestMapping("toViewProcessImage")
	public String toViewProcessImage(Model model, WorkFlowVo workFlowVo) {
		model.addAttribute("deploymentId", workFlowVo.getDeploymentId());
		return "workflow/viewProcessImage";
	}

	/**
	 * 加载流程部署信息
	 */
	@ResponseBody
	@RequestMapping("loadAllDeployment")
	public DataGridView loadAllDeploys(WorkFlowVo vo) {
		return workFlowService.queryProcessDeploy(vo);
	}

	/**
	 * 删除流程部署
	 */
	@ResponseBody
	@RequestMapping("deleteworkflow")
	public ResultCode deleteProcessDeployById(Integer id) {
		try {
			this.workFlowService.deleteProcessDeployById(id);
			return ResultCode.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除流程部署
	 */
	@ResponseBody
	@RequestMapping("batchDeleteworkflow")
	public ResultCode batchDeleteProcessDeploy(WorkFlowVo vo) {
		try {
			for (String id : vo.getIds()) {
				this.deleteProcessDeployById(Integer.parseInt(id));
			}
			return ResultCode.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.DELETE_ERROR;
		}
	}

	/**
	 * 查看部署流程图
	 */
	@ResponseBody
	@RequestMapping("viewProcessImage")
	public ResponseEntity<Object> viewProcessImage(WorkFlowVo workFlowVo) {
		String deploymentId = workFlowVo.getDeploymentId();
		InputStream is = this.workFlowService.viewProcessImage(deploymentId);

		// 把输入流转成字节数组
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
		int rc = 0;
		try {
			while ((rc = is.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 将下载的文件，封装byte[]
		byte[] bytes = swapStream.toByteArray();
		// 创建封装响应头信息的对象
		HttpHeaders header = new HttpHeaders();
		// 封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 设置下载的文件的名称
		header.setContentDispositionFormData("attachment", "saaa.jpg");

		// 创建ResponseEntity对象
		ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);

		return entity;
	}
}
