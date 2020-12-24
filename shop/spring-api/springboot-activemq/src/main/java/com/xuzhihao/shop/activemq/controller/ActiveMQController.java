package com.xuzhihao.shop.activemq.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActiveMQController {
	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	// 发送queue类型消息

	@GetMapping("/queue")
	public String sendQueueMsg(String msg) {
		this.jmsTemplate.convertAndSend(new ActiveMQQueue("queue"), msg);
		return "queue 成功";
	}

	// 发送topic类型消息
	@GetMapping("/topic")
	public String sendTopicMsg(String msg) {
		this.jmsTemplate.convertAndSend(new ActiveMQTopic("topic"), msg);
		return "topic 成功";
		
	}
}