package com.yaohua.designpattern.责任链模式.Validator的迭代过程;

import com.yaohua.designpattern.责任链模式.注解.Length;
import com.yaohua.designpattern.责任链模式.注解.Max;
import com.yaohua.designpattern.责任链模式.注解.Min;

import java.lang.reflect.Field;

/**
 * 文件名：Validator
 * 作者：huahua
 * 时间：2025/11/9 01:53
 * 描述
 */
public class Validator版本一 {


    /**
     * 初始版本的代码 ：版本一：  其实已经实现了功能， 但是我们要观察一下，有没有什么通用的逻辑；
     * 通用逻辑：：拿到一个属性，判断属性是否符合某个条件， 符合就会 根据条件进行不同的校验逻辑；
     * <p>
     * 我们找到我们代码的通用逻辑 先进性一层抽象； 基于这一点我们前往版本二；
     */
    public void validate(Object obj) throws Exception {
        //1.获取类对象 ，为了反射
        Class<?> aClass = obj.getClass();
        //2.遍历对象字段
        for (Field declaredField : aClass.getDeclaredFields()) {
            declaredField.setAccessible(true);
            //3.获取注解的值； 和当前属性的值进行校验
            Length length = declaredField.getAnnotation(Length.class);
            if (length != null) {
                //TODO 校验功能       validateLength(length,declaredField.get(obj));
            }

            Max max = declaredField.getAnnotation(Max.class);
            if (max != null) {
                //TODO 校验功能                 validateLength(max,declaredField.get(obj));
            }


            Min min = declaredField.getAnnotation(Min.class);
            if (min != null) {
                //TODO 校验功能                 validateLength(min,declaredField.get(obj));
            }


        }

    }


}
