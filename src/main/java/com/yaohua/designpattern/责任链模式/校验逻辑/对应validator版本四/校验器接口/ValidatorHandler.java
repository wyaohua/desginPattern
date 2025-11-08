package com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器接口;

import com.yaohua.designpattern.责任链模式.上下文对象.ValidatorContext;
import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;

/**
 * 文件名：ValidatorHandler
 * 作者：huahua
 * 时间：2025/11/9 02:04
 * 描述
 */
public interface ValidatorHandler {

    //传入字段的值；进行校验；
    void validate(Object value, ValidatorContext context) ;
}
