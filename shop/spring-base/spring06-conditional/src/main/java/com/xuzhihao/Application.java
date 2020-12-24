package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xuzhihao.config.SpringConfiguration;

//按条件注入 true表示注入
//Condition中获取beanFactory、registry也可以批量注册
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
