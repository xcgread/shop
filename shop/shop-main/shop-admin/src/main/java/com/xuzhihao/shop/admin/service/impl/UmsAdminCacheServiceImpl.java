package com.xuzhihao.shop.admin.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xuzhihao.shop.admin.service.UmsAdminCacheService;
import com.xuzhihao.shop.mbg.model.UmsAdmin;

/**
 * UmsAdminCacheService实现类
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Value("${redis.database}")
	private String redis_database;

	@Value("${redis.expire.common}")
	private Long redis_expire;

	@Value("${redis.key.admin}")
	private String redis_key_admin;

	@Override
	public void delAdmin(Long adminId) {
		String key = redis_database + ":" + redis_key_admin + ":" + adminId;
		redisTemplate.delete(key);
	}

	@Override
	public UmsAdmin getAdmin(Long adminId) {
		String key = redis_database + ":" + redis_key_admin + ":" + adminId;
		return (UmsAdmin) redisTemplate.opsForValue().get(key);
	}

	@Override
	public void setAdmin(UmsAdmin admin) {
		String key = redis_database + ":" + redis_key_admin + ":" + admin.getId();
		redisTemplate.opsForValue().set(key, admin, redis_expire, TimeUnit.SECONDS);
	}
}
