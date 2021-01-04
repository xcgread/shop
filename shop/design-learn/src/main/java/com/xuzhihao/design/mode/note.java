package com.xuzhihao.design.mode;

/**
 * 使用单例模式创建JDBC工具类
 */
public class note {

//	hashcode:0101 0101       与运算符 都为1才是1
//	length-1:0000 1111
//	------------------
//				  0101	
//	2进制表示整合只有一个1的时候 将高位后面的全部移走 直至剩余一个高位1
//   n = cap - 1;     或运算符 有1为1
//   n |= n >>> 1;
//   n |= n >>> 2;
//   n |= n >>> 4;
//   n |= n >>> 8;
//   n |= n >>> 16;

//	与运算符 都为1才是1
//	或运算符 有1为1
//	异或运算符 相同为0 不同为1

	// 工厂模式
	// 构建者模式
	// 代理模式
	// 责任链模式
	// 策略模式 优化if else eg:handlerMappings
	// 观察者模式
	// 单例模式
	public static void main(String[] args) {
		int a = 3;

		System.out.println(100 / 3);
	}

}