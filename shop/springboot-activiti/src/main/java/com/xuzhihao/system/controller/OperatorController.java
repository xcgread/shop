package com.xuzhihao.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.common.annotation.RefreshFilterChain;
import com.xuzhihao.common.api.ResultBean;
import com.xuzhihao.system.model.Operator;
import com.xuzhihao.system.service.OperatorService;

@Controller
@RequestMapping("/operator")
public class OperatorController {

	@Resource
	private OperatorService operatorService;

	@GetMapping("/index")
	public String index() {
		return "operator/operator-list";
	}

	@GetMapping
	public String add() {
		return "operator/operator-add";
	}

	@RefreshFilterChain
	@PostMapping
	@ResponseBody
	public ResultBean add(Operator operator) {
		operatorService.add(operator);
		return ResultBean.success();
	}

	@GetMapping("/{operatorId}")
	public String update(Model model, @PathVariable("operatorId") Integer operatorId) {
		Operator operator = operatorService.selectByPrimaryKey(operatorId);
		model.addAttribute("operator", operator);
		return "operator/operator-add";
	}

	@RefreshFilterChain
	@PutMapping
	@ResponseBody
	public ResultBean update(Operator operator) {
		operatorService.updateByPrimaryKey(operator);
		return ResultBean.success();
	}

	@GetMapping("/list")
	@ResponseBody
	public ResultBean getList(@RequestParam(required = false) Integer menuId) {
		List<Operator> operatorList = operatorService.selectByMenuId(menuId);
		return ResultBean.success(operatorList);
	}

	@RefreshFilterChain
	@DeleteMapping("/{operatorId}")
	@ResponseBody
	public ResultBean delete(@PathVariable("operatorId") Integer operatorId) {
		operatorService.deleteByPrimaryKey(operatorId);
		return ResultBean.success();
	}

	@GetMapping("/tree")
	@ResponseBody
	public ResultBean tree() {
		return ResultBean.success(operatorService.getALLMenuAndOperatorTree());
	}

}
