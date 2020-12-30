package com.xuzhihao.shop.admin.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.common.annotation.ApiLogs;
import com.xuzhihao.shop.common.aspect.WebLogAspect;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 订单管理Controller 包含敏感词
 */
@RestController
@Api(tags = "OrderController", description = "订单管理")
@RequestMapping("/order")
public class OrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

	@ApiLogs
	@ApiOperation("查询订单")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public HashMap<String, Object> list(String realName, String mobilePhone, String abc) {
		// realName,idCard,fixedPhone,bankCard,mobilePhone,address,email,cnapsCode

		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("realName", "今晚打老虎");
		parameter.put("idCard", "111111111111115762");
		parameter.put("fixedPhone", "86391536");
		parameter.put("bankCard", "6222600890987671234");
		parameter.put("mobilePhone", "13344455566");
		parameter.put("address", "辽宁省大连市金州蓝家河");
		parameter.put("email", "xcg992224@163.com");
		parameter.put("cnapsCode", "1234567890AB");
		parameter.put("desc", "我们都是好孩子");

		
		
		LOGGER.info(JSONUtil.parse(parameter).toString());
		
		parameter.put("realName", "打完老虎的日志应该是在拦截器中出现");
		return parameter;
	}
}
