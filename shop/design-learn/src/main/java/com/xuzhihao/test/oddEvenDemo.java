package com.xuzhihao.test;

//线程通讯几种方式
// 1、`synchronized`加wait/notify 休眠唤醒方式
// 2、`ReentrantLock`加Condition方式
// 3、`CountDownLatch` 闭锁方式
// 4、`CyclicBarrier` 栅栏的方式
// 5、`Semaphore` 信号量的方式
//   join的使用
//   yield的使用

public class oddEvenDemo {
	private int i = 0;// 打印的数
	private Object obj = new Object();

	public void odd() {
		// 小于10打印
		while (i < 10) {
			synchronized (obj) {
				if (i % 2 == 1) {
					System.out.println("奇数" + i);
					i++;
					obj.notify();
				} else {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // 等待偶数打印完毕
				}
			}
		}

	}

	public void even() {
		// 小于10打印
		while (i < 10) {
			synchronized (obj) {
				if (i % 2 == 0) {
					System.out.println("偶数" + i);
					i++;
					obj.notify();
				} else {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // 等待奇数打印完毕
				}
			}
		}

	}

	public static void main(String[] args) {
		oddEvenDemo addEvenDemo = new oddEvenDemo();

		new Thread(() -> {
			addEvenDemo.odd();
		}).start();

		new Thread(() -> {
			addEvenDemo.even();
		}).start();

	}
}
