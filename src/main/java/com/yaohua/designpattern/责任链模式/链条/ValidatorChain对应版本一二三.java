package com.yaohua.designpattern.责任链模式.链条;

import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本一二三.校验器接口.ValidatorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：ValidateChain
 * 作者：huahua
 * 时间：2025/11/9 02:25
 * 描述  校验链对象
 */
public class ValidatorChain对应版本一二三 {


    //这是链
    private List<ValidatorHandler> validatorHandlers = new ArrayList<>();




    /**
     * 校验的功能  : 遍历链条依次校验
     *
     * @param value 字段的具体值；
     */
    public void validate(Object value) throws ValidatoeException {

        for (ValidatorHandler validatorHandler : validatorHandlers) {
            validatorHandler.validate(value);
        }

    }


    /**
     *   添加校验器到链中；
     * @param validatorHandler  校验器的实现
     */
    public void addLastHandler(ValidatorHandler validatorHandler) {

        this.validatorHandlers.add(validatorHandler);

    }

}
