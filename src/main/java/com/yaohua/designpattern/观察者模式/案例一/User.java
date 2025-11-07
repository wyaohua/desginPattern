package com.yaohua.designpattern.观察者模式.案例一;

import java.util.function.Consumer;

/**
 * 文件名：user
 * 作者：huahua
 * 时间：2025/11/7 19:07
 * 描述
 */
public class User {

    private final String name;


    //每个用户都有自己的消费逻辑，针对不同天气 不同用户做不同的事情；
    private final Consumer<String> consumer;

    public User(String name,Consumer<String> consumer) {
        this.name = name;
        this.consumer = consumer;
    }



    //用户接收到天气的情况， 做出响应
    public void receiveInfo(String info){
        consumer.accept(info);
    }
}
