package com.xuzhihao.shop.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuzhihao.shop.common.domain.UserDto;

/**
 * Created by macro on 2020/7/16.
 */
@FeignClient("shop-portal")
public interface UmsMemberService {

	@GetMapping("/sso/loadByUsername")
	UserDto loadUserByUsername(@RequestParam String username);
}
