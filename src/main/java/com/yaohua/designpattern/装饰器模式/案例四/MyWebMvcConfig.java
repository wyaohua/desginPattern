package com.yaohua.designpattern.装饰器模式.案例四;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 文件名：MyWebMvcConfig
 * 作者：huahua
 * 时间：2025/11/2 18:18
 * 根据参考spring的源码，我们通过这个配置类将我们自己参数解析 添加到spring维护的参数解析器集合中；
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext context;



    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TimestampRequestBodyMethodProcessor(context));
    }
}
