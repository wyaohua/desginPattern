package com.yaohua.designpattern.观察者模式.案例二.监听者;

import com.yaohua.designpattern.观察者模式.案例二.事件.Event;

/**
 * 文件名：EventListener
 * 作者：huahua
 * 时间：2025/11/7 20:28
 * 描述  消息订阅者， 为了解决Main中的第5点问题；
 */
public interface EventListener {

    void onEvent(Event event);
}
