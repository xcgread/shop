package com.xuzhihao.shop.server;

import org.springframework.stereotype.Component;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ServerHandler extends SimpleChannelInboundHandler<Protocol> {

	// HEART_BEAT可以采用注册方式从容器中获取
	// 需要注意当主线程启动后阻塞导致无法通过ApplicationContextAware方式获取上下文
	private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(Unpooled
			.copiedBuffer(new Protocol(1234567890L, System.currentTimeMillis() + "").toString(), CharsetUtil.UTF_8));

	int readIdleTimes = 0;// 默认超时次数

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			switch (idleStateEvent.state()) {
			case READER_IDLE:
				readIdleTimes++; // 读空闲的计数加1
				System.out.println(ctx.channel().remoteAddress() + "-" + readIdleTimes);
				break;
			case WRITER_IDLE:
				// 不处理 写空闲
				break;
			case ALL_IDLE:
				// 不处理 读写空闲
				break;
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
		if (readIdleTimes > 3) {
			log.info("{}读空闲超过3次，关闭连接", ctx.channel().remoteAddress());
			// 服务器已经连续三次没有收到信息向客户端发送消息
//			ctx.writeAndFlush(HEART_BEAT).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
			ctx.writeAndFlush(HEART_BEAT).addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					// ctx.channel().close();
					// log.info("服务器主动关闭{}", ctx.channel().remoteAddress());
				}
			});
		}

	}

	/**
	 * 新客户端接入
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("{}连接成功", ctx.channel().remoteAddress());
	}

	/**
	 * 客户端断开
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("{}关闭连接", ctx.channel().remoteAddress());
		SocketHolder.remove((NioSocketChannel) ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Protocol protocol) throws Exception {
		log.info("收到来自客户端={}的内容：{}", ctx.channel().remoteAddress(), protocol);
		// 保存客户端与 Channel 之间的关系
//		NettySocketHolder.put(protocol.getId(), (NioSocketChannel) ctx.channel());
	}

	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
}