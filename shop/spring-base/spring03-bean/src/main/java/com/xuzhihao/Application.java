package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xuzhihao.config.SpringConfiguration;

//@Bean的注入形式,可以配置原型 初始化方法 销毁方法
//1.它写在方法上，当前返回对应存入到容器中 方法名是beanName
//2.成员上
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		ac.close();
	}

}
