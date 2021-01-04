package com.xuzhihao.test;

public class StringTest {
	/**
	 * a的值在编译时就被确定下来，故其值"xy"被放入String的驻留池(驻留池在堆中)并被a指向。
	 * b的值在编译时也被确定，那么b的值在String的驻留池中找是否有等于"xy"的值，有的话也被b指向。 故两个对象地址一致
	 * 
	 * @return true
	 */
	public static Boolean testString1() {
		String a = "xy";
		String b = "xy";
		return a == b;
	}

	/**
	 * b的值在是两个常量相加，编译时也被确定。
	 * 
	 * @return true
	 */
	public static Boolean testString2() {
		String a = "xyy";
		String b = "xy" + "y";
		return a == b;
	}

	/**
	 * b的值为一个变量和一个常量相加，无法编译时被确定,而是会在堆里新生成一个值为"abc"的对象
	 * 
	 * @return false
	 */
	public static Boolean testString3() {
		String a = "xyy";
		String b = "xy";
		b = b + "y";
		return a == b;
	}

	/**
	 * b的值都无法编译时被确定,而是会在堆里分别新生成一个对象叫"xyy"。
	 * 
	 * @return false
	 */
	public static Boolean testString4() {
		String a = "xyy";
		String b = "xy".concat("y");
		return a == b;
	}

	/**
	 * new String()创建的字符串不是常量，不能在编译期就确定，所以new String() 创建的字符串不放入常量池中，它们有自己的地址空间。
	 * a,b的值都无法编译时被确定,会在堆里分别新生成一个值为"xy"的对象。
	 * 
	 * @return fasle
	 */
	public static Boolean testString5() {
		String a = new String("xy");
		String b = new String("xy");
		return a == b;
	}

	/**
	 * intern()把驻留池中"xy"的引用赋给b。
	 * 
	 * @return true
	 */
	public static Boolean testString6() {
		String a = "xy";
		String b = new String("xy");
		b = b.intern();
		return a == b.intern();
	}

	/**
	 * char的toString方法返回的是一个char对象的字符串，而不是我们想象的"xy"
	 * 
	 * @return false
	 */
	public static Boolean testString7() {
		String b = "xy";
		char[] a = new char[] { 'x', 'y' };
		return a.toString().equals(b);
	}

	/**
	 * char是一种新的类型，不存在驻留池的概念。
	 * 
	 * @return fasle
	 */
	public static Boolean testString8() {
		String b = "xy";
		char[] a = new char[] { 'x', 'y' };
		return a.toString() == b;
	}

	/**
	 * String不可变性的体现
	 */
	String str = "xy";

	public String chage(String str) {
		str = "xyy";
		return str;
	}

	public static void main(String[] args) {
		print(testString1()); // true
		print(testString2()); // true
		print(testString3()); // fasle
		print(testString4()); // false
		print(testString5()); // false
		print(testString6()); // true
		print(testString7()); // false
		print(testString8()); // false
	}

	public static void print(Object o) {
		System.out.println(o);
	}
}