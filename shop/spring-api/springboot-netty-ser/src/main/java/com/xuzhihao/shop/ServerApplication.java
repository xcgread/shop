package com.xuzhihao.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		// 启动服务端
//		NettyServer nettyServer = new NettyServer();
//		nettyServer.start(new InetSocketAddress("172.17.17.165", 8090));
	}

}