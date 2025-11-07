package com.yaohua.designpattern.观察者模式.案例二.事件生产者;

import com.yaohua.designpattern.观察者模式.案例二.总线.TvStation;
import com.yaohua.designpattern.观察者模式.案例二.事件.WeatherEvent;

import java.util.Random;

/**
 * 文件名：WeatherStation
 * 作者：huahua
 * 时间：2025/11/7 19:07
 * 描述 天气站  ，目前只负责生产天气的消息， 去除了通知订阅者的逻辑 ；解决了main中的第2点
 */
public class WeatherStation {

    //要持有电视台
    private final TvStation tvStation;

    private final Random random = new Random();

    public WeatherStation(TvStation tvStation) {
        this.tvStation = tvStation;
    }

    //事件的发生
    public String getWeather() {
        return random.nextBoolean() ? "晴天" : "雨天";
    }



    //不停获取天气信息 并且发送给电视台；
    public void start() throws InterruptedException {
        while (true){
            String info = getWeather();
            WeatherEvent weatherEvent = new WeatherEvent(info);
            tvStation.publish(weatherEvent); //天气事件
            Thread.sleep(3000);  //定时器驱动， 每3秒触发一次事件检查和分发
        }
    }
}
