package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xuzhihao.config.SpringConfiguration;

//@import 
//1.直接注入@Import(Rainbow.class)
//2.实现导入选择器@Import(MyImportSelector.class)
//3.实现bean信息注册器//@Import({ MyImportBeanDefinitionRegistrar.class })
//前两种注入名称为全限定类名
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println(ac.getBean("Stock"));
		System.out.println(ac.getBean("Stock2"));
		ac.close();
	}

}
