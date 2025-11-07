package com.yaohua.designpattern.观察者模式.案例二;



/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/7 19:42
 * 描述  基于案例一和案例二 更加解耦；
 *   解决几个问题：
 *     1. 目前的天气站只能传输字符串的消息
 *     2. 天气站应该只负责生成天气的消息；
 *     3. 天气站不应该负责通知用户，应该把通知功能交给电视台；
 *     4. 用户目前只有一个receiveInfo 函数 是用来消费天气的，只能消费者一个事件，如果想要消费其他事件，难道要再写一个函数？ 怎么解决？
 *          4.1 事件负责发布时间给总线，总线负责接收到事件，给事件的订阅者；
 *          4.2 订阅者根据事件类型，去执行对应的逻辑；
 *     5..目前事件只能发布给 User， 因为我们消息订阅的类型者的集合是User；
 */


import com.yaohua.designpattern.观察者模式.案例二.事件.NewsEvent;
import com.yaohua.designpattern.观察者模式.案例二.事件.WeatherEvent;
import com.yaohua.designpattern.观察者模式.案例二.事件生产者.WeatherStation;
import com.yaohua.designpattern.观察者模式.案例二.总线.TvStation;
import com.yaohua.designpattern.观察者模式.案例二.监听者.User;

/**
 * 总结：观察者模式 ：消息生产者直接通知到 监听者；  （案例一）
 *      发布订阅模式： 事件生产者(气象站) 将事件交给 总线(电视台)， 总线去通知监听者（案例二）
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TvStation tvStation = new TvStation();

        // 两位用户
        User zhangsan = new User("张三");
        User lisi = new User("李四");

        // —— 给“张三”注册两种事件的消费逻辑 —— //
        zhangsan.on(WeatherEvent.class, e ->
                System.out.println("[张三·天气消费] 天气是：" + e.source() + "，时间戳：" + e.timestamp())
        );

        zhangsan.on(NewsEvent.class, e ->
                System.out.println("[张三·新闻消费] 头条：" + e.source())
        );

        // —— 给“李四”注册一种（也可注册两种） —— //
        lisi.on(WeatherEvent.class, e ->
                System.out.println("[李四·天气消费] 今天是：" + e.source())
        );

        // 订阅：按事件类型分别订阅
        tvStation.subscribe(zhangsan, WeatherEvent.class);
        tvStation.subscribe(zhangsan, NewsEvent.class);
        tvStation.subscribe(lisi, WeatherEvent.class);

        // 天气站每3秒发布一次天气
        WeatherStation weatherStation = new WeatherStation(tvStation);

        // 额外：模拟一条新闻（第二种事件）
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                tvStation.publish(new NewsEvent("国家队夺冠！"));
                Thread.sleep(5000);
                tvStation.publish(new NewsEvent("地铁新线通车～"));
            } catch (InterruptedException ignored) { }
        }).start();

        // 启动天气站
        weatherStation.start();
    }
}
