package com.xuzhihao.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

	// 考核项组数据生成 必须同时准备好才能一起同步
	public void check(String propertyGroup) {
		System.out.println(propertyGroup + "正在准备...");
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(propertyGroup + "正在同步...");
	}

	public static void main(String[] args) {
		CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
		new Thread(() -> {
			cyclicBarrierDemo.check("及时处置项");
		}).start();
		new Thread(() -> {
			cyclicBarrierDemo.check("超时项");
		}).start();
		new Thread(() -> {
			cyclicBarrierDemo.check("延期项");
		}).start();
	}

}
