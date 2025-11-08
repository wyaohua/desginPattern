package com.yaohua.designpattern.责任链模式.链条;

import com.yaohua.designpattern.责任链模式.上下文对象.ValidatorContext;
import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器接口.ValidatorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：ValidateChain
 * 作者：huahua
 * 时间：2025/11/9 02:25
 * 描述  校验链对象
 */
public class ValidatorChain对应版本四 {


    //这是链
    private List<ValidatorHandler> validatorHandlers = new ArrayList<>();


    //链的上下文对象， 让链中的每个节点不再是独立存在的，每个节点都可以通过上下文对象去控制整个链的流程；
    private final ValidatorContext validatorContext = new ValidatorContext("我是节点间传递的值哦~");

    /**
     *  版本四 ，中 功能一的实现；
     * @param value 字段的具体值；
     */
  /*
  public void validate(Object value) throws ValidatoeException {
        for (ValidatorHandler validatorHandler : validatorHandlers) {
            validatorHandler.validate(value,validatorContext);
            //判断节点是否要停止责任链的进行；
            if (validatorContext.shouldStop()) {
                break;
            }
        }
        //可能抛出异常抛出异常
        validatorContext.throwExceptionIfNecessary();

    }
*/

    /**
     * 版本四 ，中功能二的实现； 有了index 整个逻辑就能大变样
     *
     * @param value 字段的具体值；
     */
    public void validate(Object value) throws ValidatoeException {
        while (true) {
            int index = validatorContext.getCurrentIndex();
            if (index == validatorHandlers.size()) {
                break;
            }
            validatorHandlers.get(index).validate(value, validatorContext); //拿到索引的节点，进行校验；

            //条件成立：代表当前节点没有调用doNext().想要结束链的进行；
            if (index == validatorContext.getCurrentIndex()) {
                break;
            }
        }
        System.out.println(validatorContext.getValue());
        //可能抛出异常抛出异常
        validatorContext.throwExceptionIfNecessary();

    }

    /**
     * 添加校验器到链中；
     *
     * @param validatorHandler 校验器的实现
     */
    public void addLastHandler(ValidatorHandler validatorHandler) {

        this.validatorHandlers.add(validatorHandler);

    }

}
