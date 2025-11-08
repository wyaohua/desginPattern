package com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器实现;

import com.yaohua.designpattern.责任链模式.上下文对象.ValidatorContext;
import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器接口.ValidatorHandler;

/**
 * 文件名：Length
 * 作者：huahua
 * 时间：2025/11/9 02:05
 * 描述  长度校验的实现
 */
public class LengthValidator implements ValidatorHandler {

    //拿到一个max 是校验的标准
    private final Integer length;

    public LengthValidator(Integer length) {
        this.length = length;
    }



    @Override
    public void validate(Object value, ValidatorContext context)  {
        if (value instanceof  String stringValue){
            if (stringValue.length() != this.length){
                //收集异常
                context.addErrorMessage("你的值是:"+stringValue+"   但是名字的长度必须等于"+length);
            }

            System.out.println(context.getValue().toString());
            context.doNext(context.getValue().toString()+"我是length");
        }
    }
}
