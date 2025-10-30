package com.yaohua.designpattern.策略模式.inertface;


import com.yaohua.designpattern.策略模式.enums.BusinessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportBusinessType {

    //要传递一个参数，值不许是BusinessType
    BusinessType value();
}
