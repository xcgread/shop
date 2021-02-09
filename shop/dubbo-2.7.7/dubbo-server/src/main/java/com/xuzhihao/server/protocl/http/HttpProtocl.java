package com.xuzhihao.server.protocl.http;

import com.xuzhihao.api.entity.Invocation;
import com.xuzhihao.api.entity.URL;
import com.xuzhihao.server.protocl.Protocl;
import com.xuzhihao.server.protocl.http.client.HttpClient;

public class HttpProtocl implements Protocl {

    public Object invokeProtocl(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.post(url.getHost(),url.getPort(),invocation);
    }

    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHost(),url.getPort());
    }
}
