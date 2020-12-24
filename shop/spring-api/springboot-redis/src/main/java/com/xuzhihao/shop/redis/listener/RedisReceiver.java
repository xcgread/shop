package com.xuzhihao.shop.redis.listener;

import org.springframework.stereotype.Component;
/**
 * 订阅接收器
 * @author Administrator
 *
 */
@Component
public class RedisReceiver {
	/**
	 * 订阅消息
	 * 
	 * @param message
	 */
	public void receiveMessage1(String message) {
		System.out.println("收到订阅1消息->" + message);
	}

	public void receiveMessage2(String message) {
		System.out.println("收到订阅2消息->" + message);
	}
}