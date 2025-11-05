package com.yaohua.designpattern.组合模式.案例二;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/5 22:09
 * 描述
 */
public class Main {

    public static void main(String[] args) {
        //实现需求：  1+1+2+3 ； 抽象成一棵树 就可以使用组合模式

        Expression oneAndOne = new AddExperssion(new NumberExperssion(1), new NumberExperssion(1));
        Expression twoAndThree = new AddExperssion(new NumberExperssion(2), new NumberExperssion(3));
        Expression end = new AddExperssion(oneAndOne, twoAndThree);
        System.out.println(end.getValue());

    }



    //统一的接口
     interface Expression{
        int getValue();
    }


    //数字表达式,代表一个数字
    static class   NumberExperssion implements Expression{
        private final int number;

        public NumberExperssion(int number) {
            this.number = number;
        }

        @Override
        public int getValue() {
            return number;
        }
    }


    //加法表达式，代表两个数字相加
    static class AddExperssion implements Expression{

        private Expression left;
        private Expression right;

        public AddExperssion(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }


        @Override
        public int getValue() {
            return left.getValue() + right.getValue();
        }
    }



}
