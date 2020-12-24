package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xuzhihao.config.SpringConfiguration;

//扫描方式1：默认路径下的所有 
//扫描方式2：自定义扫描器(包含规则、排除规则)
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
