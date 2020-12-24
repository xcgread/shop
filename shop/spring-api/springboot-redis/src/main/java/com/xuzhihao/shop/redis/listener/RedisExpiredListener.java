package com.xuzhihao.shop.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * 自定义监听失效事件
 * @author Administrator
 *
 */
public class RedisExpiredListener implements MessageListener {
	
 
    @Override
    public void onMessage(Message message, byte[] pattern) {
         String key = new String(message.getBody());
         System.out.println("自定义监听到redis的key自动过期:"+key);
    }
 
}