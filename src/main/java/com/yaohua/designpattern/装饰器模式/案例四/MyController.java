package com.yaohua.designpattern.装饰器模式.案例四;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/2 16:51
 * 描述
 */
@RestController
@RequestMapping("/api")
public class MyController {


    /**
     * 现状：发送过来请求 拿到请求用map接收，然后直接返回
     * 需求：拿到请求在里面封装上一个时间戳，然后直接返回；
     *     做法：第一步. 需要知道spring什么时候创建的这个map；
     */


    @PostMapping
    public Map<String, Object> origin(@TimestampRequestBody Map<String, Object> map){
        return map;
    }

}
