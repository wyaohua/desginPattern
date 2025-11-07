package com.yaohua.designpattern.观察者模式.案例三.监听器;

import com.yaohua.designpattern.观察者模式.案例三.事件.RandomEvetn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 文件名：RandomEventListener
 * 作者：huahua
 * 时间：2025/11/8 00:32
 * 描述  监听器
 */
@Component
public class RandomEventListener {


    @EventListener
    public void onRandomEvent(RandomEvetn randomEvetn){
        System.out.println(randomEvetn.getSource());
    }
}
