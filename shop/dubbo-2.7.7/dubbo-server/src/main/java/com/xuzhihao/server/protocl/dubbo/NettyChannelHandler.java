package com.xuzhihao.server.protocl.dubbo;

import java.lang.reflect.Method;

import com.xuzhihao.api.entity.Invocation;
import com.xuzhihao.register.RegisterType;
import com.xuzhihao.register.factory.LocalRegisterFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class NettyChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        String interfaceName = invocation.getInterfaceName();
        Class interfaceImplClass = LocalRegisterFactory.getLocalRegister(RegisterType.LOCAL).get(interfaceName);
        Method method = interfaceImplClass.getMethod(invocation.getMethodName(),invocation.getParamtypes());
        String result = (String) method.invoke(interfaceImplClass.newInstance(),invocation.getObjects());
        ctx.write(result);
        ctx.flush();
    }
}
