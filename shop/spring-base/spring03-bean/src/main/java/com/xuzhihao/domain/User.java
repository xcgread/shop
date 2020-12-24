package com.xuzhihao.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Data;

/**
 * 用户
 * 
 * @author Administrator
 *
 */
@Data
public class User implements InitializingBean, DisposableBean {
	private int id;
	private String name;

	// 1.@PostConstruct
	// 2.InitializingBean
	// 3.initMethod
	public void initMethod() {
		System.out.println("User initMethod");

	}

	public void destroyMethod() {
		System.out.println("User destroyMethod");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("User@InitializingBean@afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("User@DisposableBean@destroy");
	}

	@PostConstruct
	void postConstruct() {
		System.out.println("User@PostConstruct====@JSR250");
	}

	@PreDestroy
	void preDestroy() {
		System.out.println("User@PreDestroy====@JSR250");
	}

	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
