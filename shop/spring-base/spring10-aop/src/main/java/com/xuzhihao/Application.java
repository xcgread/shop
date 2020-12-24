package com.xuzhihao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xuzhihao.service.AccountService;

public class Application {
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config")) {
			AccountService accountService = (AccountService) ac.getBean("proxyAccountService");
			accountService.transfer("aaa", "bbb", 100d);
		}
	}

}
