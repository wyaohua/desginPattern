package com.yaohua.designpattern.责任链模式.Validator的迭代过程;

import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器实现.LengthValidator;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器实现.MaxValidator;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器实现.MinValidator;
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
public class Validator版本二 {





    /**
     * 版本二 ：我们找到我们代码的通用逻辑 先进性一层抽象； 基于这一点我们从版本一迭代到版本二；
     * 一：实现功能：
     *      1.抽象出来一个校验器，ValidatorHandler 它的功能就是进行校验,并且抛出我们的自定义的 ValidatoeException 校验异常；
     *      2.有三个实现 LengthValidator  MaxValidator  MinValidator ，分别对应不同注解的校验逻辑
     * 二： 再进阶的思考： 我们的校验 是依据字段， 每个字段都有自己的一个或者多重的校验，也就是说每个字段都有自己的校验链； 例如我们的age属性，要有max和min两个校验；
     * 基于这个思考，我们前往版本三
     * @param obj
     * @throws Exception
     */

    public  void validate(Object  obj) throws Exception {
        //1.获取类对象 ，为了反射
        Class<?> aClass = obj.getClass();
        //2.遍历对象字段
        for (Field declaredField : aClass.getDeclaredFields()) {
            declaredField.setAccessible(true);
            //3.使用不同的校验器进行校验
            Length length = declaredField.getAnnotation(Length.class);
            if (length != null){
                new LengthValidator(length.value()).validate(declaredField.get(obj));
            }

            Max max = declaredField.getAnnotation(Max.class);
            if (max != null){
                new MaxValidator(length.value()).validate(declaredField.get(obj));
            }


            Min min = declaredField.getAnnotation(Min.class);
            if (min != null){
                new MinValidator(length.value()).validate(declaredField.get(obj));
            }



        }

    }





}
