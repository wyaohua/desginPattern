package com.yaohua.designpattern.迭代器模式.案例二;

import com.yaohua.designpattern.迭代器模式.案例一.User;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件名：UserFile迭代02
 * 作者：huahua
 * 时间：2025/11/3 22:17
 * 描述 使用迭代器模式
 */
public class UserFile迭代02 implements Iterable<User> {

    private final File file;

    public UserFile迭代02(File file) {
        this.file = file;
    }


    @Override
    public Iterator<User> iterator() {
        return new iter();
    }



    class iter implements Iterator<User>{

        int cursor;

        List<User> userList = readAllUsers();
        /**
         * 读取文件中的数据，转换为User对象
         * @return
         */
        private List<User> readAllUsers() {
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                return lines.stream().map(line->{
                     String midString = line.substring(1, line.length() - 1);
                     String[] split = midString.split(",");
                     return new User(split[0], Integer.parseInt(split[1]));
                 }).collect(Collectors.toList());
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ArrayList<>();
        }


        @Override
        public boolean hasNext() {
            return cursor != userList.size();
        }
        @Override
        public User next() {
            int currentIndex = cursor;
            cursor++;
            return userList.get(currentIndex);
        }
    }
}
