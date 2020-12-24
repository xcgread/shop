package com.xuzhihao.shop.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuzhihao.shop.common.domain.UserDto;

/**
 */
@FeignClient("shop-admin")
public interface UmsAdminService {

	@GetMapping("/admin/loadByUsername")
	UserDto loadUserByUsername(@RequestParam String username);
}
