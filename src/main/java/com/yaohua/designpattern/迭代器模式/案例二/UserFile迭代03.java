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
 * 描述   对readAllUsers函数改造 别一次性读取所有的数据，万一数据太大会OOM 或者太消耗内存； 改成了逐行读取；
 */
public class UserFile迭代03 implements Iterable<User> {

    private final File file;

    public UserFile迭代03(File file) {
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
            List<User> out = new ArrayList<>();
            try (java.io.BufferedReader br = java.nio.file.Files.newBufferedReader(file.toPath(), java.nio.charset.StandardCharsets.UTF_8)) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) continue;
                    String midString = line.substring(1, line.length() - 1);
                    String[] split = midString.split(",");
                    out.add(new User(split[0], Integer.parseInt(split[1])));
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return out;
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
