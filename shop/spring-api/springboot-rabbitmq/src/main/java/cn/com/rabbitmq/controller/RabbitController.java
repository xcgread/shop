package cn.com.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.rabbitmq.service.MqSender;

@RestController
public class RabbitController {

	@Autowired
	private MqSender mqSender;

	@GetMapping("/send1")
	private String send(@RequestParam String orderId) {
		mqSender.send1(orderId);
		return "发送即时消息成功！" + orderId;
	}
	@GetMapping("/send2")
	private String send2(@RequestParam String orderId) {
		mqSender.send2(orderId);
		return "发送延迟消息成功！" + orderId;
	}
	@GetMapping("/send3")
	private String send3(@RequestParam String orderId) {
		mqSender.send3(orderId);
		return "发送DLX消息成功！" + orderId;
	}
}