package com.yaohua.designpattern.策略模式.service;

import com.yaohua.designpattern.策略模式.enums.BusinessType;

/**
 * 维护各个业务的生成规则；
 */

public interface IdGenerateService {

    //支持什么 业务类型  不需要了 使用注解来支持
//    BusinessType support();

////判断逻辑 ,不再需要
//    boolean support(String name);


//    生成id
   public String generateId();

}
