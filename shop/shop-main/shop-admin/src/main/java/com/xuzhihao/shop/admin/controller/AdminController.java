package com.xuzhihao.shop.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.admin.dto.UmsAdminLoginParam;
import com.xuzhihao.shop.admin.service.UmsAdminService;
import com.xuzhihao.shop.admin.service.UmsRoleService;
import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.domain.UserDto;
import com.xuzhihao.shop.mbg.model.UmsAdmin;
import com.xuzhihao.shop.mbg.model.UmsRole;

import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台用户管理
 * 
 * @author Administrator
 *
 */
@Api(tags = "AdminController", description = "后台用户管理")
@RequestMapping("/admin")
@RestController
public class AdminController {

	@Autowired
	private UmsAdminService adminService;
	@Autowired
    private UmsRoleService roleService;

	@ApiOperation(value = "登录以后返回token")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CommonResult<?> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
//		if (result.hasErrors()) {
//			return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
//		}
//		if (umsAdminLoginParam.getUsername().equals("admin")) {
//			Asserts.fail(ResultCode.SUBREPEAT);
//		}
		return adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
	}

	@ApiOperation(value = "获取当前登录用户信息")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public CommonResult<?> getAdminInfo() {
		UmsAdmin umsAdmin = adminService.getCurrentAdmin();
		Map<String, Object> data = new HashMap<>();
		data.put("username", umsAdmin.getUsername());
		data.put("menus", roleService.getMenuList(umsAdmin.getId()));
		data.put("icon", umsAdmin.getIcon());
		List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
		if (CollUtil.isNotEmpty(roleList)) {
			List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
			data.put("roles", roles);
		}
		return CommonResult.success(data);
	}

	@ApiOperation("根据用户名获取通用用户信息")
	@RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
	public UserDto loadUserByUsername(@RequestParam String username) {
		UserDto userDTO = adminService.loadUserByUsername(username);
		return userDTO;
	}
}