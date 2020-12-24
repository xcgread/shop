package com.xuzhihao.shop.admin.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.common.annotation.ApiLogs;
import com.xuzhihao.shop.common.annotation.Limit;
import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.enmu.LimitType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 商品管理Controller
 */
@RestController
@Api(tags = "ProductController", description = "商品管理")
@RequestMapping("/product")
@Slf4j
public class ProductController {
	private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
	private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
	private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();
	@ApiLogs
	@ApiOperation("查询商品")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		log.info(System.currentTimeMillis() + "-查询商品");
		return System.currentTimeMillis() + "商品";
	}
	
	@Limit
	@GetMapping("/limitTest1")
	public CommonResult<Integer> testLimiter1(@RequestParam String id) {
		return CommonResult.success(ATOMIC_INTEGER_1.incrementAndGet());
	}

	@Limit(key = "customer_limit_test", limitType = LimitType.CUSTOMER)
	@GetMapping("/limitTest2")
	public CommonResult<Integer> testLimiter2(@RequestParam String id) {
		return CommonResult.success(ATOMIC_INTEGER_2.incrementAndGet());
	}

	@Limit(limitType = LimitType.IP)
	@GetMapping("/limitTest3")
	public CommonResult<Integer> testLimiter3(@RequestParam String id) {
		return CommonResult.success(ATOMIC_INTEGER_3.incrementAndGet());
	}

}
