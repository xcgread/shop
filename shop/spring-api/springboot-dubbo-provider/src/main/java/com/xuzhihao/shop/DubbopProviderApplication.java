package com.xuzhihao.shop;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、将服务提供者注册到注册中心 2、引入dubbo和zkclient相关依赖 3、配置dubbo的扫描包和注册中心地址 4、使用@Service发布服务
 * 
 * @author Administrator
 *
 */
@EnableDubbo
@SpringBootApplication
public class DubbopProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubbopProviderApplication.class, args);
	}
}
