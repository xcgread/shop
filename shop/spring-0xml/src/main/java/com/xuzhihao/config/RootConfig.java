package com.xuzhihao.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 配置RootConfig，相当于是配置spring的主容器：
 * 
 * @author admin
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.io.ssm.module" }, excludeFilters = // 不扫描以下包
{ @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { EnableWebMvc.class, ControllerAdvice.class,
		Controller.class }) })
public class RootConfig {

	private static final Log log = LogFactory.get();

//	@Bean
//	public BeanNameAutoProxyCreator proxyCreator() {
//		BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
//		proxyCreator.setProxyTargetClass(true);
//		proxyCreator.setBeanNames("*Service");
//		proxyCreator.setInterceptorNames("transactionInterceptor");
//		return proxyCreator;
//	}

}