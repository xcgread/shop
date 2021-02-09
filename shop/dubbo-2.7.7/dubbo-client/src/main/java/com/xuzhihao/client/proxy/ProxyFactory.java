package com.xuzhihao.client.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.xuzhihao.api.entity.Invocation;
import com.xuzhihao.api.entity.URL;
import com.xuzhihao.register.RegisterType;
import com.xuzhihao.register.RemoteRegister;
import com.xuzhihao.register.factory.RemoteRegisterFactory;
import com.xuzhihao.server.protocl.Protocl;
import com.xuzhihao.server.protocl.ProtoclFactory;
import com.xuzhihao.server.protocl.ProtoclType;

public class ProxyFactory {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getProxy(final ProtoclType protoclType ,final RegisterType registerType, final Class interfaceClass){
       return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
           public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               Protocl protocl = ProtoclFactory.getProtocl(protoclType);
               Invocation invocation = new Invocation(interfaceClass.getName(),method.getName(),method.getParameterTypes(),args);
               RemoteRegister remoteRegister = RemoteRegisterFactory.getRemoteRegister(registerType);
               URL radomURL = remoteRegister.getRadomURL(interfaceClass.getName());
               System.out.println("调用地址host:"+ radomURL.getHost()+ ",port:"+radomURL.getPort());
               return protocl.invokeProtocl(radomURL,invocation);
           }
       });
    }
}
