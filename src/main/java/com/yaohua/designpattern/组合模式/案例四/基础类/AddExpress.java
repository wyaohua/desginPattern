package com.yaohua.designpattern.组合模式.案例四.基础类;

/**
 * 文件名：AddExpress
 * 作者：huahua
 * 时间：2025/11/6 00:16
 * 描述 加法
 */
public class AddExpress extends BinaryPeratorExpress {


    public AddExpress(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return left.getValue() + right.getValue();
    }
}
