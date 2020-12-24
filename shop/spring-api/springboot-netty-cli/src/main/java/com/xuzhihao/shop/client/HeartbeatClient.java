package com.xuzhihao.shop.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeartbeatClient {
	private EventLoopGroup group = new NioEventLoopGroup();
	@Value("${netty.server.port}")
	private int nettyPort;
	@Value("${netty.server.host}")
	private String host;
	@SuppressWarnings("unused")
	private SocketChannel channel;

	@PostConstruct
	public void start() throws InterruptedException {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class).handler(new CustomerHandleInitializer());
		ChannelFuture future = bootstrap.connect(host, nettyPort).sync();
		if (future.isSuccess()) {
			log.info(" NettyClient启动成功");
		}
		channel = (SocketChannel) future.channel();
	}
}