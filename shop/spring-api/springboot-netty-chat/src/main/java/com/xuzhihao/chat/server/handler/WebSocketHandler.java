package com.xuzhihao.chat.server.handler;

import java.time.LocalDateTime;

import com.xuzhihao.chat.message.MessageProcess;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/*
 * 处理webSocket请求连接
 * @author Kevin
 * @date 2020/12/25 17:24
 *
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /*消息处理器*/
    private static MessageProcess messageProcess=new MessageProcess();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("聊天消息内容--》"+ "\t"+msg.text());
        String clientMsg = msg.text();
        new TextWebSocketFrame("服务器时间" + LocalDateTime.now() + " " + clientMsg);
        messageProcess.process(ctx,msg);
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //用户上线
        messageProcess.login(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //用户下线
        messageProcess.logout(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("发生异常--》"+cause.getMessage());
        cause.printStackTrace();
    }


}
