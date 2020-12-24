package com.xuzhihao.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}
}
//1、普通邮件
//2、Html邮件
//3、Html模板邮件
//4、附件邮件