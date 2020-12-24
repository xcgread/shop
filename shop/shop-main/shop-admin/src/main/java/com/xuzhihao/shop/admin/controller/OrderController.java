package com.xuzhihao.shop.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.common.annotation.ApiLogs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单管理Controller
 */
@RestController
@Api(tags = "OrderController", description = "订单管理")
@RequestMapping("/order")
@Slf4j
public class OrderController {

	@ApiLogs
	@ApiOperation("查询订单")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		log.info(System.currentTimeMillis() + "-查询订单");
		return System.currentTimeMillis() + "订单";
	}

}
