package com.xuzhihao.shop;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、引入依赖 2、配置dubbo的注册中心地址 3、引用服务
 */
@SpringBootApplication
@EnableDubbo
public class DubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboConsumerApplication.class, args);
	}

}
//- 使用zk为注册中心
//- 不使用zk可以配置url进行点对点调试
//@DubboReference(url = "${dubbo.test}")
