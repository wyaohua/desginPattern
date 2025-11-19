package com.yaohua.designpattern.建造者模式.案例一;

/**
 * 一个最经典的建造者模式:
 *  User的创建，必须要经过一些校验；且创建后属性无法修改；
 *    1。 User不可以通过构造函数创建
 *    2.  User必须通过 内部类构造出来 且创建的同时要进行校验；（保证User是合法的）
 *    3. User构建后的属性不可修改；
 *
 *
 */
public class Main {
    public static void main(String[] args) {

        User user = User.newBuilder().name("张三").age(12).build();
        System.out.println(user);

    }
}
