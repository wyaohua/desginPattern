package com.yaohua.designpattern.策略模式.impl;


import com.yaohua.designpattern.策略模式.enums.BusinessType;
import com.yaohua.designpattern.策略模式.inertface.SupportBusinessType;
import com.yaohua.designpattern.策略模式.service.IdGenerateService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//改制申请 ：编码规则：单据类别码GZS, 3位+年2位+月2位+3位流水号, 共10位组成。例：GZS25060001。


@Component
@SupportBusinessType(BusinessType.GZS)
public class GzsImpl implements IdGenerateService {




    @Override
    public String generateId() {
        return "GZS";
    }
}
