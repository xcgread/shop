package com.xuzhihao.shop.dubbo.service;

import java.util.HashMap;

import org.apache.dubbo.config.annotation.Service;

//暴露服务 注意这里使用的是dubbo提供的注解@Service,而不是Spring的
@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public HashMap<String, String> findByPid(Integer pid) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("time", System.currentTimeMillis() + "");
		return map;
	}
}
