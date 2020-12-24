package com.xuzhihao.shop.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@RequestMapping("message/send")
	public String send(String msg) {
		kafkaTemplate.send("users", msg); // 使用kafka模板发送信息
		return "success";
	}
}
