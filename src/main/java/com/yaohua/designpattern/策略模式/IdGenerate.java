package com.yaohua.designpattern.策略模式;
import com.yaohua.designpattern.策略模式.enums.BusinessType;
import com.yaohua.designpattern.策略模式.inertface.SupportBusinessType;
import com.yaohua.designpattern.策略模式.service.IdGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ID生成器，判断业务，
 */

@Component
public class IdGenerate {
    /**
     *
     * 将自动注入的List 转为Map ，防止每次都要遍历
     *
     * @Autowired private List<IdGenerateService> idGenerateServices;
     **/


    //使用函数注入 来对idGenerateServiceMap进行注入
    private Map<BusinessType, IdGenerateService> idGenerateServiceMap;


    /**
     *传入name， 找到对应的枚举类型；
     * 通过枚举类型拿到对应的实现服务，调用对应的实现，执行对应的逻辑返回结果；
     */
    @GetMapping("/getId/{name}")
    public String getId(@PathVariable("name") String name) throws IllegalAccessException {
        BusinessType businessType = BusinessType.typeOf(name);
        IdGenerateService idGenerateService = idGenerateServiceMap.get(businessType);
        return  idGenerateService.generateId();

    }

    //只有注入的时候遍历一次List ,map的key是它支持的业务类型，value就是它自己；
    @Autowired
    public void setIdGenerateServiceMap(List<IdGenerateService> idGenerateServices) throws IllegalAccessException {
        this.idGenerateServiceMap =
                idGenerateServices.stream()
                        .filter(idGenerateService -> idGenerateService.getClass().isAnnotationPresent(SupportBusinessType.class))
                                .collect(Collectors.toMap(this::findBusinessType, Function.identity()));
        if (this.idGenerateServiceMap.size() != BusinessType.values().length){
            throw new IllegalAccessException("有业务类型找不到对应的策略");
        }
    }

    private BusinessType findBusinessType(IdGenerateService idGenerateService){
        SupportBusinessType annotation = idGenerateService.getClass().getAnnotation(SupportBusinessType.class);
        return annotation.value();
    }

}
