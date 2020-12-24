package com.xuzhihao.shop.activemq.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AListener {

	@JmsListener(destination = "queue", containerFactory = "jmsListenerContainerQueue")
    public void ListenQueue(String msg) {
    	System.out.println("接收到queue消息：" + msg);
    }
	 @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")
     public void receiveStringTopic(String msg) {
     System.out.println("ATopicConsumer接收到消息...." + msg);
    }
}
