package com.xuzhihao.shop.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 监听全局14所有过期事件__keyevent@*__:expired"
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

	public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}

	/**
	 * 数据失效事件，进行数据处理
	 * 
	 * @param message
	 * @param pattern
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String key = new String(message.getBody());
		System.out.println("全局14监听到redis的key自动过期:" + key);
	}
}