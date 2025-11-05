package com.yaohua.designpattern.组合模式.案例四;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/6 01:29
 * 描述  就是在案例三的基础上 添加了变量；
 */
public class Main {


    public static void main(String[] args) {
        Map<String, Integer> map = Map.of("a", 5, "b", 6);
        ExpressionParser expressionParser = new ExpressionParser("1+15*(9+4+(1+a))+b", map);
        System.out.println(expressionParser.toExpression().getValue());
    }
}
