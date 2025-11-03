package com.yaohua.designpattern.迭代器模式.案例二;

import com.yaohua.designpattern.迭代器模式.案例一.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * 文件名：Main原始02
 * 作者：huahua
 * 时间：2025/11/3 22:15
 * 描述  原始代码的基础上 就是 把主要逻辑抽取到了 readUsers函数中；
 */
public class Main迭代01 {

    public static void main(String[] args) throws IOException {
        ArrayList<User> userArrayList = new ArrayList<>();
        readUsers(user -> userArrayList.add(user));
        System.out.println(userArrayList);

    }

    public static  void readUsers(Consumer<User> consumer) throws IOException {
        File userFile = new File("user.demo");
        List<String> lines = Files.readAllLines(userFile.toPath());

        for (String line : lines) {
            //1.将文件读取的每一行 转化为一个User对象
            String midString = line.substring(1, line.length() - 1);
            String[] split = midString.split(",");
            User user = new User(split[0], Integer.parseInt(split[1]));  //转化成了User对象

            //2.对User对象进行操作
            consumer.accept(user);


        }
    }
}
