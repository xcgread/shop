package com.xuzhihao.shop.aop.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

	public void test() {
		System.out.println("同步方法使用线程->" + Thread.currentThread().getName());
	}

	@Async
	public void asyncTest() {
		System.out.println("异步方法使用线程默认->" + Thread.currentThread().getName());
	}

	@Async("define")
	public void asyncExampleTest() {
		System.out.println("异步方法使用线程define->" + Thread.currentThread().getName());
	}

}
