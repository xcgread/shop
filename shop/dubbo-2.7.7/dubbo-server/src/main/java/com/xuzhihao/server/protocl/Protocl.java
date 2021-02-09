package com.xuzhihao.server.protocl;

import com.xuzhihao.api.entity.Invocation;
import com.xuzhihao.api.entity.URL;

public interface Protocl {
    /**
     * 远程调用
     * @param url
     * @param invocation
     */
    Object invokeProtocl(URL url, Invocation invocation);

    /**
     * 服务开启
     * @param url
     */
    void start(URL url);
}
