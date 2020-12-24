package com.xuzhihao.shop.redis.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 失效监听
 * 
 * @author Administrator
 *
 */
@Configuration
public class RedisListenerConfig {

	/**
	 * redis消息监听器容器 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
	 * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
	 * 
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter, MessageListenerAdapter listenerAdapter2) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		// 可以添加多个 messageListener
		container.addMessageListener(listenerAdapter, new PatternTopic("redisChat"));
		container.addMessageListener(listenerAdapter2, new PatternTopic("redisChat2"));
		container.addMessageListener(new RedisExpiredListener(), new PatternTopic("__keyevent@14__:expired"));

		return container;
	}

	/**
	 * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
	 * 
	 * @param redisReceiver
	 * @return
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(RedisReceiver redisReceiver) {
		System.out.println("redisChat订阅消息适配器加载完毕");
		return new MessageListenerAdapter(redisReceiver, "receiveMessage1");
	}

	@Bean
	MessageListenerAdapter listenerAdapter2(RedisReceiver redisReceiver) {
		System.out.println("redisChat2订阅消息适配器加载完毕");
		return new MessageListenerAdapter(redisReceiver, "receiveMessage2");
	}
}