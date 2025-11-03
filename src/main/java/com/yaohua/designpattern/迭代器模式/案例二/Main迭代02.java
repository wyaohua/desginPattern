package com.yaohua.designpattern.迭代器模式.案例二;

import com.yaohua.designpattern.迭代器模式.案例一.User;

import java.io.File;

/**
 * 文件名：Main迭代02
 * 作者：huahua
 * 时间：2025/11/3 22:24
 * 描述   在迭代01的基础上 使用迭代器模式进行封装；UserFile迭代02
 */
public class Main迭代02 {


    public static void main(String[] args) {
        File file = new File("user.demo");
        UserFile迭代02 userFile迭代02 = new UserFile迭代02(file);
        for (User user : userFile迭代02) {
            System.out.println(user);
        }
    }


}
