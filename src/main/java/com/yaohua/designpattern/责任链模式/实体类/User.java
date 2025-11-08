package com.yaohua.designpattern.责任链模式.实体类;

import com.yaohua.designpattern.责任链模式.注解.Length;
import com.yaohua.designpattern.责任链模式.注解.Max;
import com.yaohua.designpattern.责任链模式.注解.Min;

/**
 * 文件名：User
 * 作者：huahua
 * 时间：2025/11/9 01:48
 * 描述   一个用户 创建需要校验它的 年龄 姓名
 */
public class User {



    @Length(4)
    private final String name;


    @Min(0)
    @Max(100)
    private final Integer age ;


    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
