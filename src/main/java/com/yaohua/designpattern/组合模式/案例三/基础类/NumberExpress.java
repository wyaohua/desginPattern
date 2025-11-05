package com.yaohua.designpattern.组合模式.案例三.基础类;

/**
 * 文件名：NumberExpress
 * 作者：huahua
 * 时间：2025/11/6 00:17
 * 描述  数字表达式
 */
public class NumberExpress implements  Expression {

    private final  int value;

    public NumberExpress(int value) {
        this.value = value;

    }

    @Override
    public int getValue() {
        return value;
    }
}
