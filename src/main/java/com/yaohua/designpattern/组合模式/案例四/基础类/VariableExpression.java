package com.yaohua.designpattern.组合模式.案例四.基础类;

/**
 * 文件名：VariableExpression
 * 作者：huahua
 * 时间：2025/11/6 01:12
 * 描述
 */
public class VariableExpression implements Expression {

    private final int  value;

    public VariableExpression(int value) {
        this.value = value;
    }


    @Override
    public int getValue() {
        return value;
    }
}
