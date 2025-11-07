package com.yaohua.designpattern.观察者模式.案例二.监听者;

import com.yaohua.designpattern.观察者模式.案例二.事件.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 文件名：user
 * 作者：huahua
 * 时间：2025/11/7 19:07
 * 描述：事件订阅者。支持为不同“事件类型”注册不同的消费逻辑
 */
public class User  implements EventListener{

    private final String name;


    // handlers 事件类型，对应的消费逻辑
    private final Map<Class<? extends Event>, Consumer<Event>> handlers = new HashMap<>();


    public User(String name) {
        this.name = name;
    }


    /**
     * 注册某个“精确事件类型”的处理函数。
     * 使用示例：
     *   user.on(WeatherEvent.class, e -> System.out.println("天气：" + e.source()));
     */
    public <T extends Event> void on(Class<T> type, Consumer<? super T> consumer) {
        // 用一层包装把 Consumer<? super T> 变为 Consumer<Event>，并用 type.cast 做运行期校验
        handlers.put(type, (Event e) -> consumer.accept(type.cast(e)));
    }

    /**
     * 仅按“精确类型”查找处理器；如果没有匹配的处理器，就忽略/打印提示。
     */
    @Override
    public void onEvent(Event event) {
        Consumer<Event> h = handlers.get(event.getClass());
        if (h != null) {
            h.accept(event);
        } else {

            System.out.println("[" + name + "] 未注册此事件类型处理器：" + event.getClass().getSimpleName());
        }
    }





}
