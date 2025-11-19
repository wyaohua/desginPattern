package com.yaohua.designpattern.建造者模式.案例一;


public class User {

    private final String name;
    private final Integer age;



    private User(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }

    public static Builder newBuilder(){
        return new Builder();
    }



    /**
     * 内部类，和User强绑定
     */
    static class Builder{
        private String name;
        private Integer age;


        private Builder(){}

        public  Builder name(String name){
            this.name = name;
            return this;
        }

        public  Builder age(Integer age){
            this.age = age;
            return this;
        }


        public User build(){
            User user = new User(this);

            if (user.age < 0){
                throw new  RuntimeException("年龄不可以超出范围");
            }

            if (user.name == null){
                throw new  RuntimeException("名字不可以为空");
            }
            return user;
        }


    }

}
