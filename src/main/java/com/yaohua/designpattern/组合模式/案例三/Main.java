package com.yaohua.designpattern.组合模式.案例三;

import com.yaohua.designpattern.组合模式.案例三.基础类.Expression;

import java.util.List;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/6 00:16
 * 描述
 */
public class Main {

    public static void main(String[] args) {
            //

        ExpressionParser parser = new ExpressionParser("1+15*(9+4+(1+5))+6");
        Expression expression = parser.toExpression();
        System.out.println(expression.getValue());

    }
}
