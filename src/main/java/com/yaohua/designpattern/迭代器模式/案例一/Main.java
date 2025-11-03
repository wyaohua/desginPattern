package com.yaohua.designpattern.迭代器模式.案例一;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/3 20:42
 * 描述  这是 配合User 类实现一个迭代器
 */
public class Main {
    public static void main(String[] args) {

        User user = new User("yaohua", 18);
        for (String s : user) {
            System.out.println(s);
        }




    }
}
