package com.yaohua.designpattern.观察者模式.案例三.事件;

import org.springframework.context.ApplicationEvent;

/**
 * 文件名：UserRegisterEvent
 * 作者：huahua
 * 时间：2025/11/8 00:35
 * 描述  用户注册事件
 */
public class UserRegisterEvent extends ApplicationEvent {



    public UserRegisterEvent(Object source) {
        super(source);
    }


    //给事件添加方法；
    public String getUser(){
        return getSource().toString();
    }
}
