package com.xuzhihao;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;

public class Application {
	// https://blog.csdn.net/ly91526188/article/details/80110149
	//https://www.cnblogs.com/lusaisai/p/13121668.html 原理

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		Context context = tomcat.addContext("/", System.getProperty("java.io.tmpdir"));
		context.addLifecycleListener(
				(LifecycleListener) Class.forName(tomcat.getHost().getConfigClass()).newInstance());
		tomcat.start();
		tomcat.getServer().await();
	}
}
