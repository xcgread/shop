package com.xuzhihao.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//线程通讯几种方式
// 1、`synchronized`加wait/notify 休眠唤醒方式
// 2、`ReentrantLock`加Condition方式
// 3、`CountDownLatch` 闭锁方式
// 4、`CyclicBarrier` 栅栏的方式
// 5、`Semaphore` 信号量的方式
//   join的使用
//   yield的使用

public class oddEvenDemo2 {
	private int i = 0;// 打印的数
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void odd() {
		// 小于10打印
		while (i < 10) {
			lock.lock();
			try {
				if (i % 2 == 1) {
					System.out.println("奇数" + i);
					i++;
					condition.signal();
				} else {
					condition.await();
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				lock.unlock();
			}
		}
	}

	public void even() {
		// 小于10打印
		while (i < 10) {
			lock.lock();
			try {
				if (i % 2 == 0) {
					System.out.println("偶数" + i);
					i++;
					condition.signal();
				} else {
					condition.await();
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		oddEvenDemo2 addEvenDemo = new oddEvenDemo2();

		new Thread(() -> {
			addEvenDemo.odd();
		}).start();

		new Thread(() -> {
			addEvenDemo.even();
		}).start();

	}
}
