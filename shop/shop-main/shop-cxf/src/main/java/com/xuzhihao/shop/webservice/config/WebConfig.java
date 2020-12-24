package com.xuzhihao.shop.webservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 快速初始化三个
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	//TestInterceptor1 myInterceptor;

	/**
	 * 注册拦截器
	 * 
	 * @return
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(myInterceptor);
	}

	/**
	 * 注册过滤器
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		//filterRegistration.setFilter(new TestFilter1());
		//filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	/**
	 * 注册监听器
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public ServletListenerRegistrationBean registrationBean() {
		ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
		//registrationBean.setListener(new MyHttpSessionListener());
		return registrationBean;
	}
}
