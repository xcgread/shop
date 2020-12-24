package com.xuzhihao.shop.flux.service;

import com.xuzhihao.shop.flux.model.User;

import reactor.core.publisher.Mono;

/**
 * @Version: 1.0
 * @Desc:
 */
public interface UserSerivice {
    Mono<User> getUserById(Long id);
}
