package com.xuzhihao.shop.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.dubbo.service.TicketService;

@RestController
public class IndexController {
//	@DubboReference(url = "${dubbo.test}")
	@DubboReference
	TicketService ticketService;

	@GetMapping("/test")
	private String send(@RequestParam String id) {
		return System.currentTimeMillis() + "测试消息成功！" + id + "->" + ticketService.getTicket();
	}
}