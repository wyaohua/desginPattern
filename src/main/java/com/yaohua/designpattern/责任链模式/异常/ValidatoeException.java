package com.yaohua.designpattern.责任链模式.异常;

public class ValidatoeException extends RuntimeException {
    public ValidatoeException(String message) {
        super(message);
    }
}
