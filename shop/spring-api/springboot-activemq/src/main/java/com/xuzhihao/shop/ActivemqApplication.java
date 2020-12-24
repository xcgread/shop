package com.xuzhihao.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivemqApplication {
	public static void main(String[] args) {
		SpringApplication.run(ActivemqApplication.class, args);
	}
}
//docker run -d --name activemq2 -p 61616:61616 -p 8161:8161 webcenter/activemq  #启动mq
