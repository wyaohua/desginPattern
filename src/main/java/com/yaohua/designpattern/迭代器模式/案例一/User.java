package com.yaohua.designpattern.迭代器模式.案例一;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 文件名：User
 * 作者：huahua
 * 时间：2025/11/3 20:42
 * 描述
 */

public class User implements Iterable<String> {

    private String name;
    private int age;


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }



    @Override
    public Iterator<String> iterator() {
        //内部类式非静态的，要关联一个外部类实例对象，就是通过this实例对象去创建UserIterator，这样去关联起来；
        return this.new UserIterator();
    }







    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    class UserIterator implements Iterator<String> {

        int count = 2;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public String next() {
            count --;
            if (count == 1){
                //非静态内部类，会关联一个外部类实例对象，User.this.name就是获取外部类实例对象的name属性；
                return User.this.name;
            }
            if (count == 0){
                return age +"";  //简写
            }

            throw  new NoSuchElementException();
        }
    }



}
