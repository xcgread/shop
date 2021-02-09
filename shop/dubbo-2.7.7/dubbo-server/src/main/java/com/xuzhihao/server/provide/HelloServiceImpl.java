package com.xuzhihao.server.provide;

import com.xuzhihao.api.HelloService;

public class HelloServiceImpl implements HelloService {

	public String sayHello(String name) {
		System.out.println("hello," + name);
		return "hello " + name;
	}
}
