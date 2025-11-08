package com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器实现;

import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器接口.ValidatorHandler;

/**
 * 文件名：Length
 * 作者：huahua
 * 时间：2025/11/9 02:05
 * 描述  年龄最大值校验的实现
 */
public class MaxValidator implements ValidatorHandler {

    //拿到一个max 是校验的标准
    private final Integer max;

    public MaxValidator(Integer max) {
        this.max = max;
    }


    @Override
    public void validate(Object value) throws ValidatoeException {
        if (value instanceof  Integer intValue){
            if (intValue > this.max){
                throw  new ValidatoeException("你的值是:"+intValue+"   但是年龄不能大于"+this.max);
            }
        }


    }
}
