package com.yaohua.designpattern.适配器模式.案例一;


/**
 * 适配器模式
 *   1。我们有MacBook 能提供一个 typec的接口， charget充电器 只能通过usb接口充电；
 *   2.我们现在希望 充电器能够连接到macbook的typec接口上 进行充电，   usb和typc的接口不适配；  借助适配器模式解决这个问题：
 */
public class Main {

    public static void main(String[] args) {

        MacBook macBook = new MacBook();
        Charget charget = new Charget();

        TypeCToUsbAdapter adapter = new TypeCToUsbAdapter(macBook);

        charget.charge(adapter);

    }
}
