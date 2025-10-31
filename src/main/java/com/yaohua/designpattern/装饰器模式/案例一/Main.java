package com.yaohua.designpattern.装饰器模式.案例一;

import java.util.HashSet;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/10/31 20:40
 * 描述
 */
public class Main {

    public static void main(String[] args) {
        HistorySet<String> set = new HistorySet<>(new HashSet<String>());
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");

        set.remove("1");
        set.remove("1");
        set.remove("6");
        set.remove("5");

        System.out.println(set);
    }
}
