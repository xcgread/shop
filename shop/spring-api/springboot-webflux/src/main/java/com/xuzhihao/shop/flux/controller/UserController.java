package com.xuzhihao.shop.flux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.flux.model.User;
import com.xuzhihao.shop.flux.service.UserSerivice;

import reactor.core.publisher.Mono;

/**
 * @Version: 1.0
 * @Desc:
 */
@RestController
public class UserController {

	@Autowired
	UserSerivice userSerivice;

	@GetMapping("/getUserById/{id}")
	public Mono<User> getUserById(@PathVariable Long id) {
		return userSerivice.getUserById(id);
	}
}
