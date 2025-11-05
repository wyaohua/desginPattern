package com.yaohua.designpattern.组合模式.案例三.基础类;

/**
 * 文件名：BinaryPeratorExpress
 * 作者：huahua
 * 时间：2025/11/6 00:18
 * 描述   加减乘除表达式都是 二元运算；所以创建这个基类BinaryPeratorExpress；
 */
public abstract class BinaryPeratorExpress implements Expression{

    Expression left;
    Expression right;

    //抽象类的构造函数 尽量是被保护的，因为只有子类使用；
    protected BinaryPeratorExpress(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * 接口的方法交给子类实现
     */


}
