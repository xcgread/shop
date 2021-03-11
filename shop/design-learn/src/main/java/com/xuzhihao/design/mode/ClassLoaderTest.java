package com.xuzhihao.design.mode;

public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {

		DecodeClassLoader dcl = new DecodeClassLoader("D:\\");
		Class<?> aClass = dcl.loadClass("ClassLoaderAttachment.class");
		Object o = aClass.newInstance();
		System.out.println(o.getClass().getName());
		System.out.println(o.getClass().getClassLoader());
		aClass.getMethod("say", null).invoke(o, null);

	}

}