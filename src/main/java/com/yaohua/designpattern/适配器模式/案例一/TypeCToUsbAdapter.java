package com.yaohua.designpattern.适配器模式.案例一;


/**
 * Typc 转换为 USB 的适配器
 *
 * 1. 最终要提供USB的功能  所以实现 Usb的接口
 * 2. 需要配一个typec 组合一个typec接口
 */
public class TypeCToUsbAdapter  implements UsbSocket {


    private final TypeCSocket typeCSocket;

    public TypeCToUsbAdapter(TypeCSocket typeCSocket) {
        this.typeCSocket = typeCSocket;
    }



    @Override
    public void connectUsb() {
        typeCSocket.connectTypeC();
        //做一些转换

        System.out.println("usb连接成功");
    }
}
