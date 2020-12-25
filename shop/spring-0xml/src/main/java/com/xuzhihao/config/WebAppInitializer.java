package com.xuzhihao.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.xuzhihao.config.WebMvcConfig;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 编程式的启动web
 * 
 * @author Administrator
 *
 */
//servlet 3.0 SPI规范
public class WebAppInitializer implements WebApplicationInitializer {
	private static final Log log = LogFactory.get();

	// tomcat 启动的时候会调用 onStartup方法 为什么？
	// 传入一个ServletContext ： web上下文对象 web.xml能做的 ServletContext都能做

	// 因为servlet 3.0的一个新规范,跟tomcat没关系，tomcat是规范的实现者之一。
	// 为什么不是tomcat规范而是servlet规范？因为市面上有很多web容器，例如jetty。如果你是web容器的规范，如果换了容器，代码将不再适用。
	// SPI "你"=>这里指的是spring
	public void onStartup(ServletContext servletContext) {
		log.info("META-INF/services/org.springframework.web.SpringServletContainerInitializer");
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebMvcConfig.class);
		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("webapp", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}
}