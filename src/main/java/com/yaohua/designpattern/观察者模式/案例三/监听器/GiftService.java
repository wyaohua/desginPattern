package com.yaohua.designpattern.观察者模式.案例三.监听器;

import com.yaohua.designpattern.观察者模式.案例三.事件.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 文件名：GiftService
 * 作者：huahua
 * 时间：2025/11/8 00:39
 * 描述  礼包服务，同时是一个事件监听者， 监听用户注册后 ，发放新手礼包
 */
@Service
public class GiftService {


    @EventListener
    public void onRegister(UserRegisterEvent event){
        String user = event.getUser();
        System.out.println("给"+user+"发送新手礼包；");
    }
}
