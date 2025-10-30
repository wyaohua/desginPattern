package com.yaohua.designpattern.策略模式.enums;

import java.util.Arrays;

import java.util.function.Predicate;

public enum BusinessType {
    //每个常量，都有初始化了Predicate判断逻辑 ；
    GZS("GZS"::equals),
    PJH("PJH"::equals),
    RJH("RJH"::equals),
    DEFAULT(name -> true);

    private final Predicate<String> predecate;

    private BusinessType(Predicate<String> predecate){
        this.predecate =predecate;
    }


    //根据传入的name，遍历每一个枚举常量，找到第一个返回true的；
    public static BusinessType typeOf(String name){
        return Arrays.stream(values())
                .filter(type -> type != DEFAULT)
                .filter(type -> type.predecate.test(name))
                .findFirst()
                .orElse(DEFAULT);
    }


}
