package com.yaohua.designpattern.建造者模式.案例二;

/**
 * 建造者模式 实现sql语句的构建
 *  1.    select 和 update  分别用了 不同的 build进行构造；  这样select构造的时候 无法调用set方法  ，update构造的时候 无法调用from
 *  2.    update构建的使用 ，使用了不同的接口，来防止同一个方法重复的调用， 例如构建表名的 table方法；
 *
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Sql.select("name", "age").from("user").where(" id = 1").build());


        System.out.println(Sql.update().table("user").where(" id = 1").set("age", "12").build());
    }
}
