package com.xuzhihao.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	private CountDownLatch countDownLatch = new CountDownLatch(2);

	// 分中心计算
	public void fzx(String fzxId) {
		System.out.println(fzxId + "正在计算...");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countDownLatch.countDown();
	}

	// 总比计算
	public void cal() {
		// 等待所有分中心计算完毕
		String name = Thread.currentThread().getName();
		System.out.println(name + "正在等待...");
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("分中心计算完毕，开始计算");
	}

	public static void main(String[] args) {
		CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
		new Thread(() -> {
			countDownLatchDemo.fzx("100");
		}).start();
		new Thread(() -> {
			countDownLatchDemo.fzx("200");
		}).start();
		new Thread(() -> {
			countDownLatchDemo.cal();
		}).start();

	}

}
