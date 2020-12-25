package com.xuzhihao.config;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

@Configuration // 标识这是一个Java配置类
@EnableWebMvc // 注解用于启动Spring MVC特性
@Import({ ThymeleafConfig.class }) // 用于引入其他配置类
@ComponentScan(basePackages = { "com.xuzhihao" }, includeFilters = // 在主容器中排出了，这里需要添加扫描
{ @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { ControllerAdvice.class, Controller.class }) })
public class WebMvcConfig extends WebMvcConfigurationSupport {

	private static final Log log = LogFactory.get();

	/**
	 * 启用spring mvc 的注解（不启用只能通过显示的配置）
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/* 静态资源处理 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/public", "classpath:/static/")
				.setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
	}

	/* 解决跨域问题 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.allowCredentials(true).allowedHeaders("*").maxAge(3600);
	}

	/* 默认、视图跳转控制器 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		// objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //
		// 不序列化null的属性

		// 默认的时间序列化格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
		jackson2HttpMessageConverter.setObjectMapper(objectMapper);
		converters.add(jackson2HttpMessageConverter);
	}
}