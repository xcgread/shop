package com.xuzhihao.shop.portal.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.constant.AuthConstant;
import com.xuzhihao.shop.common.domain.UserDto;
import com.xuzhihao.shop.common.enmu.ResultCode;
import com.xuzhihao.shop.common.exception.Asserts;
import com.xuzhihao.shop.mbg.mapper.UmsMemberMapper;
import com.xuzhihao.shop.mbg.model.UmsMember;
import com.xuzhihao.shop.mbg.model.UmsMemberExample;
import com.xuzhihao.shop.portal.dto.UmsRegisterParam;
import com.xuzhihao.shop.portal.service.AuthService;
import com.xuzhihao.shop.portal.service.UmsMemberCacheService;
import com.xuzhihao.shop.portal.service.UmsMemberService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;

/**
 * 会员管理Service实现类
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
	@Autowired
	private UmsMemberMapper memberMapper;
	@Autowired
	private UmsMemberCacheService memberCacheService;
	@Autowired
	private AuthService authService;
	@Autowired
	private HttpServletRequest request;

	@Override
	public UmsMember getByUsername(String username) {
		UmsMemberExample example = new UmsMemberExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UmsMember> memberList = memberMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(memberList)) {
			return memberList.get(0);
		}
		return null;
	}

	@Override
	public void register(UmsRegisterParam umsRegisterParam) {
		// 验证验证码
		if (!verifyAuthCode(umsRegisterParam.getAuthCode(), umsRegisterParam.getTelephone())) {
			Asserts.fail("验证码错误");
		}
		// 查询是否已有该用户
		UmsMemberExample example = new UmsMemberExample();
		example.createCriteria().andUsernameEqualTo(umsRegisterParam.getUsername());
		example.or(example.createCriteria().andPhoneEqualTo(umsRegisterParam.getTelephone()));
		List<UmsMember> umsMembers = memberMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(umsMembers)) {
			Asserts.fail("该用户已经存在");
		}
		// 没有该用户进行添加操作
		UmsMember umsMember = new UmsMember();
		umsMember.setUsername(umsRegisterParam.getUsername());
		umsMember.setPhone(umsRegisterParam.getTelephone());
		umsMember.setPassword(BCrypt.hashpw(umsRegisterParam.getPassword()));
		umsMember.setCreateTime(new Date());
		umsMember.setStatus(1);
		memberMapper.insert(umsMember);
		umsMember.setPassword(null);
	}

	@Override
	public String generateAuthCode(String telephone) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		memberCacheService.setAuthCode(telephone, sb.toString());
		return sb.toString();
	}

	@Override
	public UmsMember getCurrentMember() {
		String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
		if (StrUtil.isEmpty(userStr)) {
			Asserts.fail(ResultCode.UNAUTHORIZED);
		}
		UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
		UmsMember member = memberCacheService.getMember(userDto.getId());
		if (member != null) {
			return member;
		} else {
			member = getById(userDto.getId());
			memberCacheService.setMember(member);
			return member;
		}
	}

	@Override
	public UmsMember getById(Long id) {
		return memberMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserDto loadUserByUsername(String username) {
		UmsMember member = getByUsername(username);
		if (member != null) {
			UserDto userDto = new UserDto();
			BeanUtil.copyProperties(member, userDto);
			userDto.setRoles(CollUtil.toList("前台会员"));
			return userDto;
		}
		return null;
	}

	@Override
	public CommonResult<?> login(String username, String password) {
		if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
			Asserts.fail("用户名或密码不能为空！");
		}
		Map<String, String> params = new HashMap<>();
		params.put("client_id", AuthConstant.PORTAL_CLIENT_ID);
		params.put("client_secret", "123456");
		params.put("grant_type", "password");
		params.put("username", username);
		params.put("password", password);
		return authService.getAccessToken(params);
	}

	// 对输入的验证码进行校验
	private boolean verifyAuthCode(String authCode, String telephone) {
		if (StringUtils.isEmpty(authCode)) {
			return false;
		}
		String realAuthCode = memberCacheService.getAuthCode(telephone);
		return authCode.equals(realAuthCode);
	}
}
