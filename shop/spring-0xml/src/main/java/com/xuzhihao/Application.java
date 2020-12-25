package com.xuzhihao;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class Application {
	// https://blog.csdn.net/ly91526188/article/details/80110149
	// https://www.cnblogs.com/lusaisai/p/13121668.html 原理
	//https://www.jianshu.com/p/3a3edbcd8f24 spi

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(80);
		// 设置主机名
		tomcat.setHostname("localhost");
		// tomcat存储自身信息的目录，比如日志等信息，根目录
		tomcat.setBaseDir(".");
		// 设置协议，默认就是这个协议connector.setURIEncoding(“UTF-8”);//设置编码
		String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";
		Connector connector = new Connector(DEFAULT_PROTOCOL);
		// 自动发布war
		tomcat.getHost().setDeployOnStartup(true);
		// 设置appBase为项目所在目录
		tomcat.getHost().setAppBase(System.getProperty("user.dir"));

		Context context = tomcat.addContext("/", "E:\\gitlocal2\\shop\\shop\\spring-0xml\\src\\main\\webapp");
//		Context context = tomcat.addContext("/", System.getProperty("java.io.tmpdir"));

		// addContext和addWebapp的区别：只会去初始化一个 context的资源目录(项目) 并不会加载
		// web的生命周期Tomcat的文件夹webapps，目录内是我们的项目
		// 有两种方式启动项目：1.war 2.文件夹
//		tomcat.addWebapp("/", "E:\\gitlocal2\\shop\\shop\\spring-0xml\\src\\main\\webapp");
//		tomcat.addWebapp("/","webapp");
		// 手动添加生命周期监听器
		context.addLifecycleListener(
				(LifecycleListener) Class.forName(tomcat.getHost().getConfigClass()).newInstance());
		// 启动tomcat
		tomcat.start();
		// 维持tomcat服务，否则tomcat一启动就会关闭
		tomcat.getServer().await();

	}

}
