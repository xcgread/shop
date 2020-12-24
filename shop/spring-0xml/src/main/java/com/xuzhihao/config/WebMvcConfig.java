package com.xuzhihao.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {

	}

	/* 配置内容裁决的一些选项 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.TEXT_HTML);
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

	}

	/* 默认静态资源处理器 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {

	}

	/* 拦截器配置 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}

	/* 静态资源处理 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("-------------加载静态资源-----------------");

		String[] pathPatterns = new String[] { "/static/img/**", "/static/js/**", "/static/i/**", "/static/fonts/**",
				"/static/css/**", "/static/html/**", "/**/*.html" };
		String[] resourceLocations = new String[] { "/static/img/", "/static/js/", "/static/i/", "/static/fonts/",
				"/static/css/", "/static/html/", "/" };
		registry.addResourceHandler(pathPatterns).addResourceLocations(resourceLocations)
				.setCacheControl(CacheControl.maxAge(31536000, TimeUnit.MILLISECONDS).cachePublic());
	}

	/* 解决跨域问题 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.allowCredentials(true).allowedHeaders("*").maxAge(3600);
	}

	/* 视图跳转控制器 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 采用转发的方式
		// registry.addViewController( "/" ).setViewName( "forward:/home" );
		// 直接返回页面的方式
		registry.addViewController("/").setViewName("home");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		// super.addViewControllers( registry );
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

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

	// 剔除掉不想用的解析器
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

	}

	@Override
	public Validator getValidator() {
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		return null;
	}

}