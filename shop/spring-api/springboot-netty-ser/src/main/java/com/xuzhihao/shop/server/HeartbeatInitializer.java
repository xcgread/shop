package com.xuzhihao.shop.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartbeatInitializer extends ChannelInitializer<Channel> {
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ch.pipeline()
//				.addLast("decoder", new StringDecoder())
//				.addLast("encoder", new StringEncoder())
				.addLast(new IdleStateHandler(5, 0, 0))
				.addLast(new HeartbeatDecoder())//自定义解码器
				.addLast(new HeartBeatHandler());
	}
}