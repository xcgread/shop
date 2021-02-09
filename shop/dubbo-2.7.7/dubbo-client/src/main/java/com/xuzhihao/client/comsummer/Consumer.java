package com.xuzhihao.client.comsummer;

import com.xuzhihao.api.HelloService;
import com.xuzhihao.client.proxy.ProxyFactory;
import com.xuzhihao.register.RegisterType;
import com.xuzhihao.server.protocl.ProtoclType;

public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(ProtoclType.HTTP, RegisterType.ZOOKEEPER,HelloService.class);
        String result = helloService.sayHello("xuzhihao");
        System.out.println(result);
    }
}
