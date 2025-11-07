package com.yaohua.designpattern.观察者模式.案例三.事件;

import org.springframework.context.ApplicationEvent;

/**
 * 文件名：RandomEvetn
 * 作者：huahua
 * 时间：2025/11/8 00:31
 * 描述  我们的事件
  */
public class RandomEvetn extends ApplicationEvent {


    public RandomEvetn(String source) {
        super(source);
    }
}
