package com.yaohua.designpattern.门面模式.案例一;

/**
 * 文件名：Tomcat
 * 作者：huahua
 * 时间：2025/11/6 20:44
 * 描述
 */
public class Tomcat implements Face {


   public void  init(){
       System.out.println("Tomcat初始化");
   }


   public void  loading(){
       System.out.println("Tomcat加载");
   }

    @Override
    public void start() {
        init();
        loading();
    }
}
