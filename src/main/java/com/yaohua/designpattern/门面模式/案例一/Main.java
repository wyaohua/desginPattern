package com.yaohua.designpattern.门面模式.案例一;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/6 20:43
 * 描述  一个简单的案例讲解了门面模式的核心，就是
 */
public class Main {

    public static void main(String[] args) {
        Face tomcat = new Tomcat();
        tomcat.start();


        Face  mysql = new Mysql();
        tomcat.start();
    }
}
