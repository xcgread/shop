package com.xuzhihao.test;

import org.openjdk.jol.info.ClassLayout;

public class JolCoreTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LockObj obj = new LockObj();
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}
}
class LockObj {
	private int i;
	private boolean b;

}
