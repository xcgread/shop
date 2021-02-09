package com.xuzhihao.register.factory;


import com.xuzhihao.register.RegisterType;
import com.xuzhihao.register.RemoteRegister;
import com.xuzhihao.register.local.RemoterMapRegister;
import com.xuzhihao.register.zookeeper.ZookeepRemoteRegister;
import com.xuzhihao.register.zookeeper.ZookeeperClient;

public class RemoteRegisterFactory  {
    private static RemoterMapRegister remoterMapRegister = new RemoterMapRegister();
    private static ZookeepRemoteRegister zookeepRemoteRegister = new ZookeepRemoteRegister(new ZookeeperClient());
    public static RemoteRegister getRemoteRegister(RegisterType registerType){
        switch (registerType){
            case LOCAL:return remoterMapRegister;
            case ZOOKEEPER: return zookeepRemoteRegister;
            default:return null;
        }
    }
}
