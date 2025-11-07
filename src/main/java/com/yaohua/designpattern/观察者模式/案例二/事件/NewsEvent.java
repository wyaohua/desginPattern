package com.yaohua.designpattern.观察者模式.案例二.事件;

/**
 * 文件名：NewsEvent
 * 作者：huahua
 * 描述：电视台的“突发新闻”事件，和 WeatherEvent 一样，继承 BaseEvent 以携带时间戳
 */
public class NewsEvent extends BaseEvent implements Event {

    // 事件自带的业务数据（这里示例用一条简单新闻）
    private final String headline;

    public NewsEvent(String headline) {
        this.headline = headline;
    }

    @Override
    public Object source() {
        // 统一通过 source() 暴露事件的“负载（payload）”
        return headline;
    }
}