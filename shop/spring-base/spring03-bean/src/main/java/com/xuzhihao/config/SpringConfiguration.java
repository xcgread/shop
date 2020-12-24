package com.xuzhihao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.xuzhihao.domain.User;

/**
 * spring配置文件
 * 
 * @author Administrator
 *
 */
@ComponentScan(value = "com.xuzhihao")
public class SpringConfiguration {

	@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
	public User user() {
		return new User(1, "admin");
	}
}
