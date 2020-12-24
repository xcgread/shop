package com.xuzhihao.shop.auth.config;

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
		return SwaggerProperties.builder().apiBasePackage("com.xuzhihao.shop.auth.controller").title("auth认证中心")
				.description("Auth认证中心相关接口文档").contactName("admin").version("1.0").enableSecurity(true).build();
	}
}
