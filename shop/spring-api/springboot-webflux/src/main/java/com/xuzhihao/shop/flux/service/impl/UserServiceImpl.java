package com.xuzhihao.shop.flux.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xuzhihao.shop.flux.model.User;
import com.xuzhihao.shop.flux.service.UserSerivice;

import reactor.core.publisher.Mono;

/**
 * @Version: 1.0
 * @Desc:
 */
@Service
public class UserServiceImpl implements UserSerivice {
	private static Map<Long, User> map = new HashMap<>();
	static {
		map.put(1L, new User(1L, "www.geekdigging.com", 18));
		map.put(2L, new User(2L, "极客挖掘机", 28));
	}

	@Override
	public Mono<User> getUserById(Long id) {
		return Mono.just(map.get(id));
	}
}
