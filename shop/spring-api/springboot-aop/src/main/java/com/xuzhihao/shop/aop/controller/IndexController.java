package com.xuzhihao.shop.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.aop.service.IndexService;
import com.xuzhihao.shop.common.api.CommonResult;

/**
 * 自定义线程池 aop环绕实现日志插入
 * 
 * @author Administrator
 *
 */
@RestController
public class IndexController {
	@Autowired
	private IndexService indexService;


	@RequestMapping("/async/{id}")
	public CommonResult<Long> users(@PathVariable(name = "id") String id, @RequestParam(name = "name") String name) {
		System.out.println("web请求使用线程->" + Thread.currentThread().getName());
		indexService.test();
		indexService.asyncTest();
		indexService.asyncExampleTest();
		return CommonResult.success(System.currentTimeMillis());
	}

}