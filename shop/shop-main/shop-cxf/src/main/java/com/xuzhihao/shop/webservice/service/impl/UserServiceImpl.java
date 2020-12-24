package com.xuzhihao.shop.webservice.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.domain.UserDto;
import com.xuzhihao.shop.webservice.annotation.SoapMapping;
import com.xuzhihao.shop.webservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	/**
	 * 模拟删除
	 */
	@SoapMapping(funcId = "100001")
	public CommonResult<Boolean> act100001(Map<String, String> map) {
		return CommonResult.success(true);
	}

	/**
	 * 模拟selectOne
	 */
	@SoapMapping(funcId = "200001")
	public CommonResult<UserDto> act200001(Map<String, String> map) {
		UserDto userDto = new UserDto();
		userDto.setUsername("admin");
		userDto.setPassword("123456");
		return CommonResult.success(userDto);
	}

	public CommonResult<Map<String, String>> act300001(Map<String, String> map) {
		map.put("times", System.currentTimeMillis() + "");
		return CommonResult.success(map);
	}

}
