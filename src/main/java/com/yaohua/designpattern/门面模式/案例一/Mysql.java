package com.yaohua.designpattern.门面模式.案例一;

/**
 * 文件名：Mysql
 * 作者：huahua
 * 时间：2025/11/6 20:43
 * 描述
 */
public class Mysql implements Face{


    public void init(){
        System.out.println("初始化mysql");
    }



    public void connect(){
        System.out.println("连接mysql");
    }


    @Override
    public void start() {
        init();
        connect();
    }
}
