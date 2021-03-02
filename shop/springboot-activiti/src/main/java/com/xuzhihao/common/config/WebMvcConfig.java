package com.xuzhihao.common.config;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xuzhihao.common.interceptor.RequestLogHandlerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Resource
	private RequestLogHandlerInterceptor logHandlerInterceptor;

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logHandlerInterceptor).excludePathPatterns(
				Arrays.asList("/css/**", "/fonts/**", "/images/**", "/js/**", "/lib/**", "/error"));
	}

}