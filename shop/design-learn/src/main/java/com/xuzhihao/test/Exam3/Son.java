package com.xuzhihao.test.Exam3;

//考点：
//类的初始化过程
//	1.一个类要创建实例需要先加载并初始化该类
//		main方法所在的类需要先加载和初始化
//	2.一个子类要初始化需要先初始化父类
//	3.一个类的初始化就是执行<clinit>()方法
//		<clinit>()方法包括 静态类变量和静态代码块 
//		静态代码块和变量执行顺序从上到下
//		<clinit>()方法只执行一次
//实例初始化过程  <init>()方法执行
//	1.<init>()方法可能重载有多个，有几个构造器就有几个<init>()
//	2.<init>()由非静态变量显示赋值和非静态代码块和构造器组成
//	3.执行顺序从上到下 构造器代码最后执行
//	4.每次创建实例对象，调用对应的构造器，都执行对应的<init>()方法
//	5.<init>()方法首行是super或者super(),即对应父类的<init>()方法
//方法的重写
//	1.哪些不能被重写
//		final
//		静态方法
//		private 等子类中不可见方法
//	2.对象的多态性
//		子类重写父类方法，通过子类对象调用的一定是子类重写过的方法
//		非静态方法默认的调用对象是this
//		this对象在构造器或者<init>()方法中就是正在创建的对象
public class Son extends Father {
	private int i = test();
	private static int j = method();
	static {
		System.out.print("6,");
	}

	Son() {
		System.out.print("7,");
	}

	{
		System.out.print("8,");
	}

	public int test() {
		System.out.print("9,");
		return 1;
	}

	public static int method() {
		System.out.print("10,");
		return 1;
	}

	public static void main(String[] args) {
		Son s1 = new Son();
		System.out.println();
		Son s2 = new Son();
	}
}
