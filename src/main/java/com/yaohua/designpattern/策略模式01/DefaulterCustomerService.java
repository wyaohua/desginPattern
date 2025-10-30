package com.yaohua.designpattern.策略模式01;

import org.springframework.stereotype.Component;

/**
 * 文件名：DefaulterCustomerService
 * 作者：huahua
 * 时间：2025/10/30 22:06
 * 描述
 */


@Component
public class DefaulterCustomerService implements CustomerService{


    @Override
    public String findCustomer() {
        return "找不到客服";
    }
}
