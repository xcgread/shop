package com.xuzhihao.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.common.api.DataGridView;
import com.xuzhihao.common.api.ResultCode;
import com.xuzhihao.system.model.vo.WorkFlowVo;
import com.xuzhihao.system.service.IWorkFlowService;

/**
 * 流程模型
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/workflow")
public class ProcessDefinitionController {
	@Autowired
	private IWorkFlowService workFlowService;

	/**
	 * 跳转到模型管理界面
	 */
	@RequestMapping("list")
	public String toWorkFlowModel() {
		return "workflow/process-list";
	}

	/**
	 * 加载模型信息
	 */
	@ResponseBody
	@RequestMapping("loadAllProcessDefinition")
	public DataGridView loadAllProcessDefinition(WorkFlowVo vo) {
		return workFlowService.queryProcessModel(vo);
	}

	/**
	 * 在线设计模型
	 */
	@ResponseBody
	@RequestMapping("createOnlineModel")
	public DataGridView onlineModel() {
		return this.workFlowService.onlineModel();
	}

	/**
	 * 发布模型为流程定义
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deployModel")
	public ResultCode deployModel(String id) {
		return this.workFlowService.deployModel(id);
	}

	/**
	 * 删除模型
	 */
	@ResponseBody
	@RequestMapping("deleteProcessModel")
	public ResultCode deleteProcessModel(Integer id) {
		try {
			this.workFlowService.deleteProcessModelById(id);
			return ResultCode.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除模型
	 */
	@ResponseBody
	@RequestMapping("batchdeleteProcessModel")
	public ResultCode batchdeleteProcessModel(WorkFlowVo vo) {
		try {
			for (String id : vo.getIds()) {
				this.deleteProcessModel(Integer.parseInt(id));
			}
			return ResultCode.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.DELETE_ERROR;
		}
	}
}