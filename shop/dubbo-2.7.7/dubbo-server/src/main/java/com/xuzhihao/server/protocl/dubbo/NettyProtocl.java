package com.xuzhihao.server.protocl.dubbo;

import com.xuzhihao.api.entity.Invocation;
import com.xuzhihao.api.entity.URL;
import com.xuzhihao.server.protocl.Protocl;
import com.xuzhihao.server.protocl.dubbo.client.NettyClient;

public class NettyProtocl implements Protocl {
    public Object invokeProtocl(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHost(),url.getPort(),invocation);
    }

    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHost(),url.getPort());
    }
}
