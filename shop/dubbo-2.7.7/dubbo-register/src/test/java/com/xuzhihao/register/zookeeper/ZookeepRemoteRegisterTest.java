package com.xuzhihao.register.zookeeper;

import org.junit.Before;
import org.junit.Test;

import com.xuzhihao.api.HelloService;
import com.xuzhihao.api.entity.URL;

public class ZookeepRemoteRegisterTest {
    ZookeepRemoteRegister remoteRegister;
    @Before
    public void setUp() throws Exception {
        remoteRegister = new ZookeepRemoteRegister(new ZookeeperClient());
    }

    @Test
    public void register() {
        remoteRegister.register(HelloService.class.getName(),new URL("debug-registry",8021));
    }

    @Test
    public void getRadomURL() {
        register();
        remoteRegister.getRadomURL(HelloService.class.getName());
    }
}