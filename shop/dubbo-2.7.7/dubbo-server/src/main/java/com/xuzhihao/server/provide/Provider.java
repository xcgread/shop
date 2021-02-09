package com.xuzhihao.server.provide;

import com.xuzhihao.api.HelloService;
import com.xuzhihao.api.entity.URL;
import com.xuzhihao.register.LocalRegister;
import com.xuzhihao.register.RegisterType;
import com.xuzhihao.register.RemoteRegister;
import com.xuzhihao.register.factory.LocalRegisterFactory;
import com.xuzhihao.register.factory.RemoteRegisterFactory;
import com.xuzhihao.server.protocl.Protocl;
import com.xuzhihao.server.protocl.ProtoclFactory;
import com.xuzhihao.server.protocl.ProtoclType;

public class Provider {
	public static void main(String[] args) {
		URL url = new URL("localhost", 2181);
		// 远程服务注册地址
		RemoteRegister register = RemoteRegisterFactory.getRemoteRegister(RegisterType.ZOOKEEPER);
		register.register(HelloService.class.getName(), url);

		// 本地注册服务的实现类
		LocalRegister localRegister = LocalRegisterFactory.getLocalRegister(RegisterType.LOCAL);
		localRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

		Protocl protocl = ProtoclFactory.getProtocl(ProtoclType.HTTP);
		protocl.start(url);
	}
}
