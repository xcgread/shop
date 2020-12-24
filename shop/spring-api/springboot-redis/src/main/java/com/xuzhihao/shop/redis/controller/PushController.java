package com.xuzhihao.shop.redis.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据推送测试
 * 
 * @author Administrator
 *
 */
@RestController
public class PushController {
	@Autowired
	private StringRedisTemplate redisTemplate;

	@RequestMapping("/pipeset")
	public String pipeset(String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 100000; i++) {
			map.put("ke你们y" + i, "我们value" + i);
		}
		long s = System.currentTimeMillis();
		RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		redisTemplate.executePipelined(new RedisCallback<Object>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				map.forEach((key, value) -> {
					connection.set(serializer.serialize(key), serializer.serialize(value), Expiration.seconds(6000),
							RedisStringCommands.SetOption.UPSERT);
				});
				return null;
			}
		}, serializer);
		return "耗时：" + (System.currentTimeMillis() - s);
	}
	@RequestMapping("/pipeget")
	public String pipeget(String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 100000; i++) {
			map.put("ke你们y" + i, "我们value" + i);
		}
		long s = System.currentTimeMillis();
		RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		List<Object> redisResult=redisTemplate.executePipelined(new RedisCallback<Object>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				map.forEach((key, value) -> {
					connection.set(serializer.serialize(key), serializer.serialize(value), Expiration.seconds(6000),
							RedisStringCommands.SetOption.UPSERT);
					connection.get(serializer.serialize(key));
				});
				return null;
			}
		}, serializer);
		return redisResult.size()+"耗时：" + (System.currentTimeMillis() - s);
	}
	
}