package com.xuzhihao.shop.activemq.controller;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
public class ActiveMQConfig {
	// queue模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(
			ConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	// topic模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(
			ConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setPubSubDomain(true);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}
}