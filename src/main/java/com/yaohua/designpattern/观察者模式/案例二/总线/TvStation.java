package com.yaohua.designpattern.观察者模式.案例二.总线;

import com.yaohua.designpattern.观察者模式.案例二.事件.Event;
import com.yaohua.designpattern.观察者模式.案例二.监听者.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 文件名：TvStation
 * 作者：huahua
 * 时间：2025/11/7 20:11
 * 描述：事件发布器（中枢）。管理“事件类型 → 订阅者列表”的映射。
 *  - subscribe：某个监听器订阅某种事件
 *  - unsubscribe：退订
 *  - publish：发布事件，分发给订阅了该“事件类型（或其父类/接口）”的监听器
 *
 * 线程安全说明：
 *  - 使用 ConcurrentHashMap + CopyOnWriteArrayList，保证读多写少的订阅/发布场景稳定。
 */
public class TvStation {




    // key = 事件类型；value = 订阅该“精确类型”的监听器列表
    //使用ConcurrentHashMap， 分发是读多写少；ConcurrentHashMap + CopyOnWriteArrayList 在这种模式下非常稳。
    private final Map<Class<? extends Event>, List<EventListener>> listenerMap = new ConcurrentHashMap<>();


    /**
     * 订阅：监听器订阅某个“精确事件类型”
     */
    public void subscribe(EventListener listener, Class<? extends Event> eventType) {
        listenerMap
                .computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                .add(listener);
    }



    /**
     * 发布事件：只把事件发给“订阅了该事件实际类型”的监听器。
     * 例如：发布 WeatherEvent 只通知订阅了 WeatherEvent.class 的人，
     *      不会再去通知订阅了 Event.class 或其父接口的人。
     */
    public void publish(Event event) {
        Class<? extends Event> actualType = event.getClass().asSubclass(Event.class);
        List<EventListener> listeners = listenerMap.get(actualType);
        if (listeners == null) return;

        for (EventListener listener : listeners) {
            try {
                listener.onEvent(event);
            } catch (RuntimeException ex) {
                // 防止单个监听器异常影响其它监听器
                System.err.println("[TvStation] 分发到 " + listener.getClass().getSimpleName()
                        + " 时出错：" + ex.getMessage());
            }
        }
    }

    /**
     * 退订（可选）
     */
    public void unsubscribe(EventListener listener, Class<? extends Event> eventType) {
        List<EventListener> listeners = listenerMap.get(eventType);
        if (listeners != null) {
            listeners.remove(listener);
            if (listeners.isEmpty()) {
                listenerMap.remove(eventType);
            }
        }
    }






}
