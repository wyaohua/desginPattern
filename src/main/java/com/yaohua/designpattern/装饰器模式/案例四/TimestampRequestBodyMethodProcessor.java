package com.yaohua.designpattern.装饰器模式.案例四;

import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.Map;

/**
 * 参考spring源码我们实现自己的参数解析器；用来解析标注了TimestampRequestBody的参数并且是map类型的；
 * 问题1：如何拿到 RequestResponseBodyMethodProcessor；
 * 问题2：如何将我们的参数解析器交给spring；
 *
 */
public class TimestampRequestBodyMethodProcessor implements HandlerMethodArgumentResolver {


    private RequestResponseBodyMethodProcessor processor;


    private ApplicationContext applicationContext;


    public TimestampRequestBodyMethodProcessor(ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TimestampRequestBody.class);
    }

    /**
     * 增强方法
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        setupProcessor();
        Object res = processor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        if (!(res instanceof Map<?, ?>)) {
            return res;
        }
        ((Map) res).put("timestamp", System.currentTimeMillis());
        return res;
    }

    public void setupProcessor() {
        if (this.processor != null) return;


        RequestMappingHandlerAdapter handlerAdapter =
                this.applicationContext.getBean(RequestMappingHandlerAdapter.class);
        for (HandlerMethodArgumentResolver argumentResolver : handlerAdapter.getArgumentResolvers()) {
            if (argumentResolver instanceof RequestResponseBodyMethodProcessor) {
                this.processor = (RequestResponseBodyMethodProcessor) argumentResolver;
            }
        }

    }
}
