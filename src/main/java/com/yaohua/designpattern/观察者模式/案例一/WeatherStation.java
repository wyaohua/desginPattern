package com.yaohua.designpattern.观察者模式.案例一;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 文件名：WeatherStation
 * 作者：huahua
 * 时间：2025/11/7 19:07
 * 描述 天气站
 */
public class WeatherStation {


    private Set<User> users =new HashSet<>();


    //提供函数 让用户订阅天气
    public void  subscribe(User user){
        this.users.add(user);
    }




    //事件的发生
    public String getWeather() {
        if (new Random().nextBoolean()){
            return "晴天";
        }
        return "雨天";
    }



    //不停获取天气信息 并且发送给订阅的用户；
    public void start() throws InterruptedException {
        while (true){
            String info = getWeather();  //事件产生
            for (User user : users) {
                user.receiveInfo(info);  //事件分发
            }
            Thread.sleep(3000);  //定时器驱动， 每3秒触发一次事件检查和分发
        }
    }
}
