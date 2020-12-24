package com.xuzhihao.shop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.domain.UserDto;
import com.xuzhihao.shop.mbg.model.UmsMember;
import com.xuzhihao.shop.portal.dto.UmsRegisterParam;
import com.xuzhihao.shop.portal.service.UmsMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员登录注册管理Controller Created by macro on 2018/8/3.
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
	@Autowired
	private UmsMemberService memberService;

	@ApiOperation("会员注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<?> register(@Validated @RequestBody UmsRegisterParam umsRegisterParam, BindingResult result) {
		memberService.register(umsRegisterParam);
		return CommonResult.success(null, "注册成功");
	}

	@ApiOperation("会员登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<?> login(@RequestParam String username, @RequestParam String password) {
		return memberService.login(username, password);
	}

	@ApiOperation("获取会员信息")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<UmsMember> info() {
		UmsMember member = memberService.getCurrentMember();
		return CommonResult.success(member);
	}

	@ApiOperation("获取验证码")
	@RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<?> getAuthCode(@RequestParam String telephone) {
		String authCode = memberService.generateAuthCode(telephone);
		return CommonResult.success(authCode, "获取验证码成功");
	}
	@ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        return memberService.loadUserByUsername(username);
    }

}
