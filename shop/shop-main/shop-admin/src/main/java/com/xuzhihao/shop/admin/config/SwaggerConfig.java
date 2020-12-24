package com.xuzhihao.shop.admin.config;

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
		return SwaggerProperties.builder().apiBasePackage("com.xuzhihao.shop.admin.controller").title("管理后台系统")
				.description("管理后台相关接口文档").contactName("admin").version("1.0").enableSecurity(true).build();
	}
}
