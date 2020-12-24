package com.xuzhihao.shop.aop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xuzhihao.shop.aop.interceptor.MyInterceptor;

/**
 * web配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private MyInterceptor aInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(aInterceptor).addPathPatterns("/**");

	}

}
