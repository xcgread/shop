package com.xuzhihao.design.mode;

/**
 * 使用单例模式创建JDBC工具类
 */
public class note {
	// 工厂模式
	// 构建者模式
	// 代理模式
	// 责任链模式
	// 策略模式 优化if else eg:handlerMappings
	// 观察者模式
	// 单例模式

	public static void main(String[] args) {
		int i = 1;
		i = i++;
		int j = i++;
		int k = i + ++i * i++;
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		//赋值= 最后计算
		//从左到右依次压入操作数栈
		//实际计算按照运算符优先级
		//自增 自减直接操作局部变量表的值 不经过操作数栈
		//最后赋值之前的结果 存储在操作数栈中
	}
}