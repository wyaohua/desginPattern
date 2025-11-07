package com.yaohua.designpattern.观察者模式.案例二.事件;

/**
 * 文件名：Event
 * 作者：huahua
 * 时间：2025/11/7 20:20
 * 描述  事件， 解决了 main中的第一点，内容用对象表示了；
 */
public interface Event {


    //时间戳
    long timestamp();

    //不同事件携带不同的内容
    Object source();
}
