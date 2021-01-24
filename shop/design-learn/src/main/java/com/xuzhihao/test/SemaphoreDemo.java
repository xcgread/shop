package com.xuzhihao.test;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	static class Work implements Runnable {
		private int workerNum;// 工号
		private Semaphore Semaphore;// 机器数量

		public Work(int workerNum, java.util.concurrent.Semaphore semaphore) {
			this.workerNum = workerNum;
			Semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				// 获取机器资源
				Semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + "获取到机器，开始工作");
				Thread.sleep(1000L);
				Semaphore.release();
				System.out.println(Thread.currentThread().getName() + "工作完毕，释放机器");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		int workers = 8;
		Semaphore Semaphore = new Semaphore(3);
		for (int i = 0; i < workers - 1; i++) {
			new Thread(new Work(i, Semaphore)).start();
		}
	}
}
