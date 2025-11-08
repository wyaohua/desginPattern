package com.yaohua.designpattern.责任链模式;


import com.yaohua.designpattern.责任链模式.Validator的迭代过程.Validator版本四;
import com.yaohua.designpattern.责任链模式.实体类.User;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/9 01:49
 * 注意: 版本四： 我们的责任链的创建基础是根据 字段；也就是说 目前只有 name 1个校验， age2个校验；  就算3个都不通过， name的单独抛出异常， age的两个合在一起抛出来异常；
 *             除非我们以对象为基础去创建责任链；才能让 name 和age 不同字段的校验异常 合在一起抛出来；
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("wuyh",18);
        Validator版本四 validator = new Validator版本四();
        validator.validate(user);
    }
}
