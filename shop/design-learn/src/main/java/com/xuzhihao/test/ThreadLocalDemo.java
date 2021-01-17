package com.xuzhihao.test;

public class ThreadLocalDemo {
	static class Bank {
		private ThreadLocal<Integer> threadlocal = new ThreadLocal<Integer>() {
			protected Integer initialValue() {
				return 0;
			}
		};

		public Integer get() {
			return threadlocal.get();
		}

		public void set(Integer money) {
			threadlocal.set(threadlocal.get() + money);
		}
	}

	static class Transfer implements Runnable {
		private Bank bank;

		public Transfer(Bank bank) {
			this.bank = bank;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				bank.set(10);
				System.out.println(Thread.currentThread().getName() + "账户余额：" + bank.get());
			}

		}
	}

	public static void main(String[] args) {
		Bank bank = new Bank();
		Transfer transfer = new Transfer(bank);
		new Thread(transfer, "客户1").start();
		new Thread(transfer, "客户2").start();
	}

}
