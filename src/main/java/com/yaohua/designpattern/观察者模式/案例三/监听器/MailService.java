package com.yaohua.designpattern.观察者模式.案例三.监听器;

import com.yaohua.designpattern.观察者模式.案例三.事件.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 文件名：MailService
 * 作者：huahua
 * 时间：2025/11/8 00:42
 * 描述 邮件服务， 监听用户注册，后给用户发送新手邮件
 */
@Service
public class MailService {


    @EventListener
    public void onRegister(UserRegisterEvent event) {
        String user = event.getUser();
        System.out.println("给"+user+"发送新手邮件");
    }

}
