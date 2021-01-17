package com.xuzhihao.shop.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuzhihao.shop.admin.dao.UmsAdminRoleRelationDao;
import com.xuzhihao.shop.admin.service.AuthService;
import com.xuzhihao.shop.admin.service.UmsAdminCacheService;
import com.xuzhihao.shop.admin.service.UmsAdminService;
import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.common.api.ResultCode;
import com.xuzhihao.shop.common.constant.AuthConstant;
import com.xuzhihao.shop.common.domain.UserDto;
import com.xuzhihao.shop.common.exception.Asserts;
import com.xuzhihao.shop.mbg.mapper.UmsAdminMapper;
import com.xuzhihao.shop.mbg.model.UmsAdmin;
import com.xuzhihao.shop.mbg.model.UmsAdminExample;
import com.xuzhihao.shop.mbg.model.UmsRole;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * UmsAdminService实现类
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

	@Autowired
	private UmsAdminMapper adminMapper;
	@Autowired
	private AuthService authService;
	@Autowired
	private UmsAdminCacheService adminCacheService;
	@Autowired
	private UmsAdminRoleRelationDao adminRoleRelationDao;
	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDto loadUserByUsername(String username) {
		// 获取用户信息
		UmsAdmin admin = getAdminByUsername(username);
		if (admin != null) {
			List<UmsRole> roleList = getRoleList(admin.getId());
			UserDto userDTO = new UserDto();
			BeanUtils.copyProperties(admin, userDTO);
			if (CollUtil.isNotEmpty(roleList)) {
				List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName())
						.collect(Collectors.toList());
				userDTO.setRoles(roleStrList);
			}
			return userDTO;
		}
		return null;
	}

	@Override
	public UmsAdmin getAdminByUsername(String username) {
		UmsAdminExample example = new UmsAdminExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UmsAdmin> adminList = adminMapper.selectByExample(example);
		if (adminList != null && adminList.size() > 0) {
			return adminList.get(0);
		}
		return null;
	}

	public CommonResult<?> login(String username, String password) {
		if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
			Asserts.fail("用户名或密码不能为空！");
		}
		Map<String, String> params = new HashMap<>();
		params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
		params.put("client_secret", "123456");
		params.put("grant_type", "password");
		params.put("username", username);
		params.put("password", password);
		return authService.getAccessToken(params);
	}

	@Override
	public UmsAdmin getCurrentAdmin() {
		String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
		if (StrUtil.isEmpty(userStr)) {
			Asserts.fail(ResultCode.UNAUTHORIZED);
		}
		UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
		UmsAdmin admin = adminCacheService.getAdmin(userDto.getId());
		if (admin != null) {
			return admin;
		} else {
			admin = adminMapper.selectByPrimaryKey(userDto.getId());
			adminCacheService.setAdmin(admin);
			return admin;
		}
	}

	@Override
	public List<UmsRole> getRoleList(Long adminId) {
		return adminRoleRelationDao.getRoleList(adminId);
	}

}
