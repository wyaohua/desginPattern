package com.yaohua.designpattern.迭代器模式.案例二;

import com.yaohua.designpattern.迭代器模式.案例一.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：Main01
 * 作者：huahua
 * 时间：2025/11/3 22:07
 * 描述  原始代码
 */
public class Main原始代码 {


    public static void main(String[] args) throws IOException {
        ArrayList<User> userArrayList = new ArrayList<>();

        File userFile = new File("user.demo");
        List<String> lines = Files.readAllLines(userFile.toPath());
        for (String line : lines) {
            String midString = line.substring(1, line.length() - 1);
            String[] split = midString.split(",");
            User user = new User(split[0], Integer.parseInt(split[1]));
            userArrayList.add(user);
        }

        System.out.println(userArrayList);

    }



}
