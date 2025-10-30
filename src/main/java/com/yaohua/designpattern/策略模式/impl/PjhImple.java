package com.yaohua.designpattern.策略模式.impl;
import com.yaohua.designpattern.策略模式.enums.BusinessType;
import com.yaohua.designpattern.策略模式.inertface.SupportBusinessType;
import com.yaohua.designpattern.策略模式.service.IdGenerateService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//排产计划：编码规则：单据类别码PJH，3位+年2位+月2位+3位流水号+1位产线码，共11位组成。产线码：一厂是B、二厂是C。例：PJH2506001C。
@Component
@SupportBusinessType(BusinessType.PJH)
public class PjhImple implements IdGenerateService {



    @Override
    public String generateId() {

        return "PJH";
    }
}
