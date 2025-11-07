package com.yaohua.designpattern;

import com.yaohua.designpattern.观察者模式.案例三.事件.RandomEvetn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DesignPatternApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DesignPatternApplication.class, args);
        run.publishEvent(new RandomEvetn("观察则模式，案例三"));


    }

}
