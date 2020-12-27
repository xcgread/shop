package com.xuzhihao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xuzhihao.config.handle.LoginFilter;
import com.xuzhihao.config.handle.LoginHandlerInterceptor;
import com.xuzhihao.config.handle.LoginSessionListener;

/**
 * 快速初始化监听器 拦截器 过滤器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private LoginHandlerInterceptor loginHandlerInterceptor;
	@Autowired
	private LoginFilter loginFilter;
	@Autowired
	private LoginSessionListener loginSessionListener;

	/**
	 * 注册拦截器
	 * 
	 * @return
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/index.html", "/",
				"/user/login");
	}

	/**
	 * 注册过滤器
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean // 将方法中返回的对象注入到IOC容器中
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(loginFilter); // 创建并注册LoginFilter
		filterRegistration.addUrlPatterns("/*"); // 拦截的路径（对所有请求拦截）
		filterRegistration.setName("LoginFilter"); // 拦截器的名称
		filterRegistration.setOrder(1); // 拦截器的执行顺序。数字越小越先执行
		return filterRegistration;
	}

	/**
	 * 注册监听器
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletListenerRegistrationBean registrationBean() {
		ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
		registrationBean.setListener(loginSessionListener);
		return registrationBean;
	}
}
