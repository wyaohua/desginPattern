package com.yaohua.designpattern.适配器模式.案例一;


/**
 * 充电器
 */
public class Charget {


    public void charge(UsbSocket usbSocket){
        usbSocket.connectUsb();
        System.out.println("充电器 连接了Usb插座，开始充电");
    }
}
