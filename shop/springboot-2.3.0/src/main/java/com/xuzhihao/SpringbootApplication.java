package com.xuzhihao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//java 元注解：修饰注解的注解
//@Target(ElementType.TYPE) 定义注解的适用范围
//@Retention(RetentionPolicy.RUNTIME) 注解的生命周期 SOURCE（源码期间 编译后消失） CLASS（检查性操作）
//@Documented javadoc
//@Inherited 修饰的注解可以被子类继承
//@SpringBootConfiguration 等同于@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class SpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
