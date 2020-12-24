package com.xuzhihao.shop.portal.config;

import org.springframework.context.annotation.Configuration;

import com.xuzhihao.shop.common.config.BaseSwaggerConfig;
import com.xuzhihao.shop.common.domain.SwaggerProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

	@Override
	public SwaggerProperties swaggerProperties() {
		return SwaggerProperties.builder().apiBasePackage("com.xuzhihao.shop.portal.controller").title("前台门户")
				.description("前台门户相关接口文档").contactName("admin").version("1.0").enableSecurity(true).build();
	}
}
