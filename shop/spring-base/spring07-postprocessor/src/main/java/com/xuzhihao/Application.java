package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.xuzhihao.scanner.MyScanner;
import com.xuzhihao.service.UserService;

//所有后置处理器PostProcessor
//通过FactoryBean模拟了mybatis的导入mapper过程
@ComponentScan("com.xuzhihao")
@MyScanner("com.xuzhihao.mapper")
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Application.class);
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println(ac.getBean("user100"));
		System.out.println(ac.getBean("user200"));
		UserService userService = ac.getBean(UserService.class);
		userService.test();
		ac.close();
	}

}
