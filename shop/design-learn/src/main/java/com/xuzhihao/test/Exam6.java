package com.xuzhihao.test;

//就近原则
//	变量的分类 .
//		成员变量：类变量和实例变量
//		局部变量
//	非静态代码块的执行：每次创建实例都会执行
//	方法调用规则：调用一次执行一次
public class Exam6 {
//	变量存储位置
//	局部变量：栈
//	实例变量：堆
//	类变量 ：方法区
	static int s;// 成员变量 类变量 存储在：方法区
	int i;// 成员变量 实例变量 存储在：堆
	int j;// 成员变量 实例变量 存储在：堆
	{
		int i = 1;
		i++;
		j++;
		s++;
	}

	public void test(int j) {// 形参局部变量 j
		j++;
		i++;
		s++;
	}

	public static void main(String[] args) {
		Exam6 obj1 = new Exam6();//局部变量 存储在：栈
		Exam6 obj2 = new Exam6();
		obj1.test(10);
		obj1.test(20);
		obj2.test(30);
		System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
		System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
	}
}
