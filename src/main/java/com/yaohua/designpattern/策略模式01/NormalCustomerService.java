package com.yaohua.designpattern.策略模式01;

import org.springframework.stereotype.Component;

/**
 * 文件名：NormalCustomerService
 * 作者：huahua
 * 时间：2025/10/30 21:30
 * 描述 :普通玩家找客服的实现类
 */
@Component
@SupportuserType(UserType.normal)
public class NormalCustomerService  implements  CustomerService{



    @Override
    public String findCustomer() {
        return "普通玩家找到一个客服，从普通客服列表中，根据情况，返回一个普通客服";
    }
}
