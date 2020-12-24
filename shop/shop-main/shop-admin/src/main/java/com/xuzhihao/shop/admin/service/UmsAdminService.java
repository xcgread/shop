package com.xuzhihao.shop.admin.service;

import java.util.List;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.domain.UserDto;
import com.xuzhihao.shop.mbg.model.UmsAdmin;
import com.xuzhihao.shop.mbg.model.UmsRole;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {

	/**
	 * 根据用户名获取后台管理员
	 */
	UmsAdmin getAdminByUsername(String username);

	/**
	 * 获取用户信息
	 */
	UserDto loadUserByUsername(String username);

	/**
	 * 登录功能
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 调用认证中心返回结果
	 */
	CommonResult<?> login(String username, String password);

	/**
	 * 获取当前登录后台用户
	 */
	UmsAdmin getCurrentAdmin();

	/**
	 * 获取用户对于角色
	 */
	List<UmsRole> getRoleList(Long adminId);

}
