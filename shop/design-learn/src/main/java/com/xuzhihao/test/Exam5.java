package com.xuzhihao.test;

/**
 * 实现f(n):求n步台阶，一共有集中走法，每次可以走一步或者两步
 * 
 * @author admin
 *
 */

//方法调用自身称为递归，利用变量的原始值推断出新的值称为迭代

//迭代：
//	优点：执行效率高，因为时间只因为循环次数而增加，没有额外空间的开销
//	缺点：代码相对可读性不好
public class Exam5 {
	public static int f(int n) {
		if (n == 1 || n == 2) {
			return n;
		}
		return f(n - 2) + f(n - 1);
	}

	public static int loop(int n) {
		if (n == 1 || n == 2) {
			return n;
		}
		int one = 2;// 初始走两步的走法
		int two = 1;// 初始走一步的走法
		int sum = 0;
		for (int i = 3; i <= n; i++) {
			// 最后走两步+最后走一步
			sum = one + two;
			two = one;
			one = sum;
		}
		return sum;
	}

	public static void main(String[] args) {
		// 递归调用
		System.out.println(f(10));
		// 迭代调用
		System.out.println(loop(10));
	}
}
