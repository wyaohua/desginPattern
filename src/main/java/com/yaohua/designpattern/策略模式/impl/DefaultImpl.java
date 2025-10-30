package com.yaohua.designpattern.策略模式.impl;

import com.yaohua.designpattern.策略模式.enums.BusinessType;
import com.yaohua.designpattern.策略模式.inertface.SupportBusinessType;
import com.yaohua.designpattern.策略模式.service.IdGenerateService;
import org.springframework.stereotype.Component;


@Component
@SupportBusinessType(BusinessType.DEFAULT)
public class DefaultImpl implements IdGenerateService {




    @Override
    public String generateId() {
        return "找不到编码规则！！！";
    }
}
