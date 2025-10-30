package com.yaohua.designpattern.策略模式01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文件名：CustomerController
 * 作者：huahua
 * 时间：2025/10/30 21:12
 * 描述
 */

@Component
public class CustomerController {



    private Map<UserType, CustomerService> customerServiceMap;

    @Autowired
    private DefaulterCustomerService defaulterCustomerService;


    @Autowired
    public void  setCustomerServiceMap(List<CustomerService> customerServices){
        this.customerServiceMap = customerServices.stream()
                .filter(customerService -> customerService.getClass().isAnnotationPresent(SupportuserType.class))
                .collect(Collectors.toMap(this::findUserType, Function.identity()));

        if (this.customerServiceMap.size() != UserType.values().length){
            throw  new RuntimeException("用户类型和策略的数量不一致");
        }
    }


    @GetMapping("customer/{recharge}")  //路径参数    ，还有一种？的是查询参数
    public String customer(@PathVariable(value = "recharge") int recharge){
        UserType userType = UserType.typeOf(recharge);

        CustomerService customerService = customerServiceMap.get(userType);

        //兜底的一个默认策略
        if (customerService == null){
            return  defaulterCustomerService.findCustomer();
        }
        return customerService.findCustomer();

    }

    //获取当前服务，支持那种用户类型
    private UserType findUserType(CustomerService customerService){
        SupportuserType annotation = customerService.getClass().getAnnotation(SupportuserType.class);
        return annotation.value();
    }





}
