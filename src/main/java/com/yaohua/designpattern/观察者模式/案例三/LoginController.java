package com.yaohua.designpattern.观察者模式.案例三;

import com.yaohua.designpattern.观察者模式.案例三.事件.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名：LoginController
 * 作者：huahua
 * 时间：2025/11/8 00:17
 * 描述  学习spring种的 发布订阅
 */


/**
 * 我们的案例二中总线 就包含了 发布和广播的功能 集中在了一起；
 * 而spring 的事件发布 ，只是spring发布了一个事件
 *           广播，决定事件如何传递给监听者；
 * 我们的案例二中的总线，是循环遍历所有监听者然后发送消息给他们，那么每一个都要等待上面的处理完成；这是一个缺点；
 * spring的默认和我们的一样都是串行，但是 spring将 发布 和广播 分开了，那么我们想要扩展并行，只需要扩展ApplicationEventMulticaster 广播器，不需要扩展发布器；这是他的优点；解耦
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    //实现了事件发布接口，所以spring容器也是一个事件发布者；（总线）
    @Autowired
    private ApplicationContext context;


    //发布事件，通知订阅者，（总线）
    @Autowired
    private ApplicationEventPublisher publisher;


    //spring不会把事件直接发送给订阅者，而是发送给事件广播器 ApplicationEventMulticaster
    @Autowired
    private ApplicationEventMulticaster multicaster;


    /**
     * 发布事件，用户注册事件
     * @param user
     * @return
     */
    @GetMapping
    public String register(String user){
        System.out.println("张三注册了");
        publisher.publishEvent(new UserRegisterEvent(user));

        return "ok";
    }


}
