package com.xuzhihao.shop.redis.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;

/**
 * stringRedisTemplate、template并存
 * 
 * @author Administrator
 *
 */
@RestController
public class TemplateController {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Object> serializeRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@RequestMapping("/set")
	public String setValue(String id) {
		stringRedisTemplate.opsForValue().set(id, "-1", 30, TimeUnit.SECONDS);
		redisTemplate.opsForValue().set(id+"A", "-1", 30, TimeUnit.SECONDS);
		return "success";
	}

	@RequestMapping("/push")
	public String push(String id) {
		stringRedisTemplate.convertAndSend("redisChat", id + "_" + Math.random());
		stringRedisTemplate.convertAndSend("redisChat2", id + "_" + Math.random());
		return "success";
	}

	@RequestMapping("/setbyte")
	public String setbyte(String id) {
		ArrayList<HashMap<String, Object>> l = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", IdUtil.fastSimpleUUID());
			map.put("time", LocalDate.now());
			l.add(map);
		}
		long s = System.currentTimeMillis();
		serializeRedisTemplate.opsForValue().set(id, ObjectUtil.serialize(l));
		return "耗时：" + (System.currentTimeMillis() - s);
	}

	@RequestMapping("/getbyte")
	public String getbyte(String id) {
		byte[] bytes = (byte[]) serializeRedisTemplate.opsForValue().get(id);
		List<HashMap<String, Object>> rtn = ObjectUtil.unserialize(bytes);
		serializeRedisTemplate.opsForValue().set(id, "-1", 30, TimeUnit.SECONDS);
		return "success" + rtn.size();
	}
}