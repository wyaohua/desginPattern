package com.yaohua.designpattern.责任链模式.Validator的迭代过程;

import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器实现.LengthValidator;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器实现.MaxValidator;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器实现.MinValidator;
import com.yaohua.designpattern.责任链模式.注解.Length;
import com.yaohua.designpattern.责任链模式.注解.Max;
import com.yaohua.designpattern.责任链模式.注解.Min;
import com.yaohua.designpattern.责任链模式.链条.ValidatorChain对应版本一二三;

import java.lang.reflect.Field;

/**
 * 文件名：Validator
 * 作者：huahua
 * 时间：2025/11/9 01:53
 * 描述
 */
public class Validator版本三 {




    /**
     * 一：版本三：我们的校验 是依据字段每个字段应该有自己的校验链，基于这个思考 我们来到了版本三；
     *
     * 二：实现功能：
     *      1. 我们遍历字段的时候我们要先拿到每个字段的校验链； 所以我们实现一个组链的逻辑；
     *      2. ValidatorChain这是我们实现的链对象， 里面有一个list作为链每个元素都是校验器， 并且提供 整个链依次校验功能 和  添加校验器到链中的功能；
     *      3. 封装方法 buildValidateChain()， 功能是依据字段，组装出它的校验链；返回一个链对象；
     *      4. 然后根据 把字段的值交给 检验链对象，继续宁校验
     *
     * 三： 思考： 目前我们再Main函数测试 版本三。 根据测试结果：
     *             我们发现我们因为先添加的 LengthValidator校验器，那么就会限制性Length的校验，length的校验不通过，后面就不会校验；
     *             我觉得应该校验所有的，把所有的校验都爆出来给用户；
     *
     *  所有的校验器都执行，将所有未通过校验的信息一次性暴露给用户；带着这个功能去版本四
     */

    public  void validate(Object  obj) throws ValidatoeException, IllegalAccessException {
        //1.获取类对象 ，为了反射
        Class<?> aClass = obj.getClass();
        //2.遍历对象字段
        for (Field declaredField : aClass.getDeclaredFields()) {
            declaredField.setAccessible(true);

            //3.拿到校验链，然后传入字段的值，进行校验；
           ValidatorChain对应版本一二三 validatorChain = buildValidateChain( declaredField );
            validatorChain.validate(declaredField.get(obj));




        }

    }

    /**
     * 版本三 拓展出来的函数
     * 根据属性，组件 链对象的函数;
     * 如果满足条件就将对应的校验器添加到链中；
     *
     * 这段代码 还可以优化， 使用工厂模式 或者 享元模式 或者 校验池
     * @param declaredField
     * @return
     */

    private ValidatorChain对应版本一二三 buildValidateChain(Field declaredField) {
        ValidatorChain对应版本一二三 validatorChain = new ValidatorChain对应版本一二三();

        Length length = declaredField.getAnnotation(Length.class);
        if (length != null) {
            validatorChain.addLastHandler(new LengthValidator(length.value()));
        }

        Max max = declaredField.getAnnotation(Max.class);
        if (max != null) {
            validatorChain.addLastHandler(new MaxValidator(max.value()));
        }

        Min min = declaredField.getAnnotation(Min.class);
        if (min != null) {
            validatorChain.addLastHandler(new MinValidator(min.value()));
        }
        return validatorChain;
    }



}
