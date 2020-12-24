package com.xuzhihao.shop.portal.service;

import org.springframework.transaction.annotation.Transactional;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.domain.UserDto;
import com.xuzhihao.shop.mbg.model.UmsMember;
import com.xuzhihao.shop.portal.dto.UmsRegisterParam;

/**
 * 会员管理Service
 */
public interface UmsMemberService {

	/**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);
	/**
	 * 用户注册
	 */
	@Transactional
	void register(UmsRegisterParam umsRegisterParam);

	/**
	 * 生成验证码
	 */
	String generateAuthCode(String telephone);

	/**
	 * 获取当前登录会员
	 */
	UmsMember getCurrentMember();

	/**
	 * 登录后获取token
	 */
	CommonResult<?> login(String username, String password);

	/**
	 * 根据会员编号获取会员
	 */
	UmsMember getById(Long id);

	/**
	 * 获取用户信息
	 */
	UserDto loadUserByUsername(String username);
}
