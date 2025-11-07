package com.yaohua.designpattern.观察者模式.案例二.事件;

/**
 * 文件名：BaseEvent
 * 作者：huahua
 * 时间：2025/11/7 20:24
 * 描述  一个事件的基础类，为了就是每次事件的实现类创建自动 记录timestamp时间戳
 */
public abstract class BaseEvent implements Event {


    private final long timestamp;
    //让构造方法 保护起来，子类才能使用；
    protected BaseEvent(){
        timestamp =System.currentTimeMillis();
    }

    @Override
    public long timestamp() {
        return timestamp;
    }
}
