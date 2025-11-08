package com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器实现;

import com.yaohua.designpattern.责任链模式.上下文对象.ValidatorContext;
import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器接口.ValidatorHandler;

/**
 * 文件名：MinValidator
 * 作者：huahua
 * 时间：2025/11/9 02:06
 * 描述 年龄最小值校验的实现
 */
public class MinValidator  implements ValidatorHandler {

    //拿到一个min 是校验的标准
    private final Integer min;

    public MinValidator(Integer min) {
        this.min = min;
    }



    @Override
    public void validate(Object value, ValidatorContext context) {
        if (value instanceof  Integer intValue){
            if (intValue < this.min){
                //收集异常
                context.addErrorMessage("你的值是:"+intValue+"   但是年龄不能小于"+this.min);
            }
            System.out.println(context.getValue().toString());
            context.doNext(context.getValue().toString()+"我是min");
        }
    }
}
