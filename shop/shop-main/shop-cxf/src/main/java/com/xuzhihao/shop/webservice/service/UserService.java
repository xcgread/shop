package com.xuzhihao.shop.webservice.service;

import java.util.Map;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.domain.UserDto;

/**
 * 用户登录
 *
 */
public interface UserService {
	CommonResult<Boolean> act100001(Map<String, String> map);

	CommonResult<UserDto> act200001(Map<String, String> map);

	CommonResult<Map<String,String>> act300001(Map<String, String> map);

}
