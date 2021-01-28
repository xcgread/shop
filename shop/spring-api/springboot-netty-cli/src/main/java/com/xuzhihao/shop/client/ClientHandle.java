package com.xuzhihao.shop.client;

import org.springframework.stereotype.Component;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientHandle extends SimpleChannelInboundHandler<ByteBuf> {


	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
				log.info("客户端已经 10 秒没有发送信息！");
				// 向服务端发送消息
				Protocol heartBeat = SpringBeanFactory.getBean(Protocol.class);
				ctx.writeAndFlush(heartBeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
		// 从服务端收到消息时被调用
		log.info("客户端收到消息={}", in.toString(CharsetUtil.UTF_8));
	}

}