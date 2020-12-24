package com.xuzhihao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuzhihao.mapper.UserMapper;

@Component
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public void test() {
		System.out.println("执行了方法");
		System.out.println(userMapper.selectByPrimaryKey(1L));
	}
}
