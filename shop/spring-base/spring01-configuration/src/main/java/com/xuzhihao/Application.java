package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xuzhihao.config.SpringConfiguration;

//注册配置文件方式：扫描范围在配置文件重定义 配置文件无需标记@Configuration
// 扫描路径方式：配置文件必须标记@Configuration 无须定义@ComponentScan
// 两种方式都可以使用@import
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.xuzhihao");
		// 2.根据id获取对象
//		JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);
		// 3.执行操作
//		jdbcTemplate.update("insert into account(id,name)values(?,?)", "111", "lisi");
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		ac.close();
	}

}
