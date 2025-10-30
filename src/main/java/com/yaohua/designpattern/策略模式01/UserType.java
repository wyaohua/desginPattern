package com.yaohua.designpattern.策略模式01;

/**
 * 文件名：UserType
 * 作者：huahua
 * 时间：2025/10/30 21:42
 * 描述
 */


import java.util.function.IntPredicate;

/**
 * 用户类型，对应的是什么金额呢？   也就是 我们用户根据什么金额返回呢？
 */
public enum UserType {

    //每个用户类型，维护自己支持的金额逻辑；  外界传入进来金额，我来判断是否是我的类型；
    normal(recharge-> recharge >0 && recharge <100);

    private final   IntPredicate predicate;

    UserType(IntPredicate predicate){
        this.predicate = predicate;
    }


    public static UserType typeOf(int recharge){
        for (UserType value : values()) {
            if (value.predicate.test(recharge)){
                return value;
            }
        }
        return null;
    }





}
