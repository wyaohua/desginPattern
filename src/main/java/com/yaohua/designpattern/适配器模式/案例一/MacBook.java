package com.yaohua.designpattern.适配器模式.案例一;

/**
 * macBook 身上有typec的接口
 */
public class MacBook implements TypeCSocket{
    @Override
    public void connectTypeC() {
        System.out.println("连接了macbook的Typec接口");
    }
}
