package com.xuzhihao.shop.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.shop.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * 首页内容管理Controller Created by macro on 2019/1/28.
 */
@Controller
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
@Slf4j
public class HomeController {

	@ApiOperation("首页内容页信息展示")
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<?> content() {
		log.info(System.currentTimeMillis() + "首页内容页信息展示");
		return CommonResult.success("首页展示信息");
	}
}
