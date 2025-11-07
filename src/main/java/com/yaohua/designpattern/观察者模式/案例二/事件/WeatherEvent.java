package com.yaohua.designpattern.观察者模式.案例二.事件;

/**
 * 文件名：WeatherEvent
 * 作者：huahua
 * 时间：2025/11/7 20:22
 * 描述  天气事件的实现
 */
public class WeatherEvent extends BaseEvent implements Event {

    private final  String info;

    //创建天气事件 的同时要获取到，天气消息；
    public WeatherEvent(String info) {
        this.info = info;
    }


    //天气事件的内容就是 天气
    @Override
    public Object source() {
        return info;
    }
}
