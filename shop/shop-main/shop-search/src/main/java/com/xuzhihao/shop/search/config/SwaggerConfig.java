package com.xuzhihao.shop.search.config;

import org.springframework.context.annotation.Configuration;

import com.xuzhihao.shop.common.config.BaseSwaggerConfig;
import com.xuzhihao.shop.common.domain.SwaggerProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2API文档的配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

	@Override
	public SwaggerProperties swaggerProperties() {
		return SwaggerProperties.builder().apiBasePackage("com.xuzhihao.shop.search.controller").title("搜索系统")
				.description("搜索相关接口文档").contactName("admin").version("1.0").enableSecurity(false).build();
	}
}
