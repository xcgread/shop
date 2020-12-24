package com.xuzhihao.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.xuzhihao.config.WebMvcConfig;

//该类从官方文档拷贝，主要目的初始化spring容器，注册servlet和注册servlet-mapping
public class MyWebApplicationInitializer implements WebApplicationInitializer {
	// ServletContext代替web.xml
	public void onStartup(ServletContext servletContext) {
		// Load Spring web application configuration
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebMvcConfig.class);

		// Create and register the DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}
}