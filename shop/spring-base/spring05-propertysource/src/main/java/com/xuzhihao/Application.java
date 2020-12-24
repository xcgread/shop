package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xuzhihao.config.SpringConfiguration;

//@PropertySource读取配置文件内容
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);
		jdbcTemplate.update("insert into account(id,name)values(?,?)", "1231456", "lisi");
		System.out.println(ac.getBean("port"));

		ac.close();
	}

}
