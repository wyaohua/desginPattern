package com.yaohua.designpattern.责任链模式.上下文对象;

import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：ValidatorContext
 * 作者：huahua
 * 时间：2025/11/9 03:14
 * 描述   校验链的上下文对象  在版本四引出的一个对象
 */
public class ValidatorContext {


    //存储异常信息
    private final  List<String> errorMessage = new ArrayList<>();

    //控制是否直接停止责任链
    private boolean stop =false;


    //配合 doNext 方法使用
    private int index =0;



    private Object value ;


    public ValidatorContext(Object value) {
        this.value = value;
    }

    /**
     * 收集异常的功能
     * @param errorMessage
     */

    public void addErrorMessage(String errorMessage){
        this.errorMessage.add(errorMessage);
    }


    /**
     *  判断是否有异常信息，有的话抛出；
     */
    public void throwExceptionIfNecessary() throws ValidatoeException{
        if (errorMessage.isEmpty()){
            return;
        }

        throw new ValidatoeException(errorMessage.toString());
    }


    /**
     * 修改停止标记
     */
    public void stopChain(){
        this.stop = true;
    }

    /**
     * 判断是否应该停止，就是让外界知道stop的值
     */
    public  boolean shouldStop(){
        return this.stop;
    }


    /**
     * 是否流转下个节点的功能
     */
    public void doNext(Object value ){
        this.value = value;
        this.index++;
    }

    /**
     * 获取当前流转到哪个节点
     */
    public int getCurrentIndex(){
        return this.index;
    }


    /**
     * 获取value 这个value可不是字段的值； 是节点间传递的一个值；
     * @return
     */
    public Object getValue() {
        return value;
    }

}
