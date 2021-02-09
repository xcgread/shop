package com.xuzhihao.register.factory;


import com.xuzhihao.register.LocalRegister;
import com.xuzhihao.register.RegisterType;
import com.xuzhihao.register.local.LocalMapRegister;

public class LocalRegisterFactory {

    private static LocalMapRegister localMapRegister = new LocalMapRegister();
    public static LocalRegister getLocalRegister(RegisterType registerType){
        switch (registerType){
            case LOCAL: return localMapRegister;
            default:return null;
        }
    }
}
