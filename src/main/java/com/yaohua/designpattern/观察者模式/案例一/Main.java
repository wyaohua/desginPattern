package com.yaohua.designpattern.观察者模式.案例一;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/7 19:06
 * 描述
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        User zhangsan = new User("张三", item -> System.out.println("张三不管什么天气都睡觉" + item));
        User lisi = new User("李四", item -> System.out.println("李四不管什么天气都打游戏" + item));


        WeatherStation weatherStation = new WeatherStation();
        //张三李四订阅信息
        weatherStation.subscribe(zhangsan);
        weatherStation.subscribe(lisi);



        weatherStation.start();
    }
}
