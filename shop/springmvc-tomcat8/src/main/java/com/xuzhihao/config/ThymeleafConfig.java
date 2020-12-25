package com.xuzhihao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * thymeleaf模板配置 ps：这里需要ApplicationContext 解决方法2种：
 * 1、实现ApplicationContextAware接口，重写setApplicationContext方法 
 * 2、ITemplateResolver
 * templateResolver（）定义为一个bean
 */
@Configuration
public class ThymeleafConfig /* implements ApplicationContextAware */ {


	// 生成视图解析器并未解析器注入模板引擎
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		// 设置编码，否则中文乱码
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}
	
	// 生成模板引擎并为模板引擎注入模板解析器
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		return engine;
	}

	// 配置生成模板解析器
	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		// resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("/WEB-INF/templates");
		resolver.setSuffix(".html");
		// 默认是TemplateMode.HTML
		resolver.setTemplateMode(TemplateMode.HTML);
		// 设置编码，否则中文乱码
		resolver.setCharacterEncoding("UTF-8");
		// 设置缓存，默认是true
		resolver.setCacheable(true);
		return resolver;
	}

}