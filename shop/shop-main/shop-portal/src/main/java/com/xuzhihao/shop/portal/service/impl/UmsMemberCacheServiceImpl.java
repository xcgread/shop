package com.xuzhihao.shop.portal.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xuzhihao.shop.mbg.model.UmsMember;
import com.xuzhihao.shop.portal.service.UmsMemberCacheService;

/**
 * UmsMemberCacheService实现类 Created by macro on 2020/3/14.
 */
@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Value("${redis.database}")
	private String redis_database;

	@Value("${redis.expire.common}")
	private Long redis_expire;

	@Value("${redis.expire.authCode}")
	private Long redis_expire_auth_code;

	@Value("${redis.key.member}")
	private String redis_key_member;

	@Value("${redis.key.authCode}")
	private String redis_key_auth_code;

	@Override
	public void delMember(Long memberId) {
		String key = redis_database + ":" + redis_key_member + ":" + memberId;
		redisTemplate.delete(key);
	}

	@Override
	public UmsMember getMember(Long memberId) {
		String key = redis_database + ":" + redis_key_member + ":" + memberId;
		return (UmsMember) redisTemplate.opsForValue().get(key);
	}

	@Override
	public void setMember(UmsMember member) {
		String key = redis_database + ":" + redis_key_member + ":" + member.getId();
		redisTemplate.opsForValue().set(key, member, redis_expire,TimeUnit.SECONDS);
	}

	@Override
	public void setAuthCode(String telephone, String authCode) {
		String key = redis_database + ":" + redis_key_auth_code + ":" + telephone;
		redisTemplate.opsForValue().set(key, authCode, redis_expire_auth_code,TimeUnit.SECONDS);
	}

	@Override
	public String getAuthCode(String telephone) {
		String key = redis_database + ":" + redis_key_auth_code + ":" + telephone;
		return (String) redisTemplate.opsForValue().get(key);
	}
}
