package com.xuzhihao.test;

//考点：
// 赋值= 最后计算
// 从左到右依次压入操作数栈
// 实际计算按照运算符优先级
// 自增 自减直接操作局部变量表的值 不经过操作数栈
// 最后赋值之前的结果 存储在操作数栈中

//JVM虚拟机规范相关指令
public class Exam1 {

	public static void main(String[] args) {
		int i = 1;
		i = i++;
		int j = i++;
		int k = i + ++i * i++;
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);

	}
}
