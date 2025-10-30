一：策略模式
    本质：对行为的抽象；
    场景：系统中多个业务都需要获取唯一的标识；每个业务都有自己的编码规则；
    使用的策略模式：1.对行为进行抽象 2.实现具体的逻辑策略；3.选择不同的策略；4.调用策略具体的逻辑


版本一： 函数中：if else 判断传入的业务标识 ，执行不同的逻辑返回不同的id；
        （做了两件事：第一件事判断是什么业务， 第二件事执行业务的id生成逻辑）
        代码如下：

   ```java
 @GetMapping("/getId/{name}")
public  String  getId(String name){
    if ("GZS".equals(name)){
        return gzsImpl.getId();
    }else if ("PJH".equals(name)){
        return pjhImpl.getId();
    }else if ("XJH".equals(name)){
        return rjhImpl.getId();
    }
    return "找不到对应的编码规则";
}
   
   ```

版本二: 将我们的第二件事执行业务的id生成逻辑进行抽取 IdGenerateService,以及不同业务对应的实体类；



版本三：现在代码的问题就是 我们在什么条件下拿到什么样的策略？ 那就是第一件事选择的逻辑，判断的逻辑；这些条件是有意义的；把条件也抽象到IdGenerateService中；
代码如下： 自动注入会把所有实现了接口的并且被spring容器管理的类注入；
         找不到对应的策略就会抛出异常；
         并且再加一个默认的策略逻辑规则；这个规则就是直接返回找不到编码规则；     private List<IdGenerateService> idGenerateServices;这里面的顺序问题了；要使用@Order，让默认的在最后一个进行遍历；
```java

@RestController("/test")
public class IdGenerate {


    @Autowired
    private List<IdGenerateService> idGenerateServices;


    @GetMapping("/getId/{name}")
    public  String  getId(@PathVariable("name") String name) throws IllegalAccessException {
        for (IdGenerateService idGenerateService : idGenerateServices) {
            if (idGenerateService.support(name)) {
                return idGenerateService.generateId();
            }
        }
        throw new IllegalAccessException(name + "无法找到对应的编码逻辑处理器");
    }
}

```

版本四：总结：至此我们已经对if else进行了优化；我们把本来的每个判断逻辑和对应的实现，都抽象出来到每个类中；我们在主流程中拿到所有的策略； 
            此时，我们的把选择策略的逻辑  和 策略 都交给了，策略本身；（例如 接口中有两个函数 一个是判断逻辑函数， 一个是执行的策略函数）



版本五：我们也可以把我们的选择的逻辑交给 主流程来做；
      定义枚举类，业务类型 BusinessType；
      List<IdGenerateService> idGenerateServices;中定义一个函数 返回的就是枚举类；这个函数就是我支持什么业务类型 ；并且删除掉boolean support(String name); 用来判断的逻辑；因为我们把选择的逻辑交给了主流程
      代码如下：选择的逻辑交给了主流程controller中的analysisBusinessType();来判断返回一个业务类型枚举，如果当前策略支持该业务类型就执行它的策略；
    
```java

@RestController("/test")
public class IdGenerate {


    @Autowired
    private List<IdGenerateService> idGenerateServices;


    @GetMapping("/getId/{name}")
    public  String  getId(@PathVariable("name") String name) throws IllegalAccessException {
        BusinessType businessType = analysisBusinessType(name);
        for (IdGenerateService idGenerateService : idGenerateServices) {
            if (idGenerateService.support().equals(businessType)) {
                return idGenerateService.generateId();
            }
        }
        throw new IllegalAccessException(name + "无法找到对应的编码逻辑处理器");
    }


    private BusinessType analysisBusinessType(String name){
        if ("GZS".equals(name)){
            return BusinessType.GZS;
        }
        if ("RJH".equals(name)){
            return BusinessType.RJH;
        }
        if ("PJH".equals(name)){
            return BusinessType.PJH;
        }
        return null;
    }

}
```



版本六：在版本五中有两个问题：
        第一点是 if else 又出现了；（解决思路：我们的枚举中每一个业务类型都对应一个意义，就是选择逻辑我们的业务类型是对应着什么样的的业务标识符号的；
        枚举的改造如下：
            提供一个构造函数，Predicate<String> support，这是一个lambda表达式，返回一个boolean值；
            提供一个私有属性；来保存这个lambda表达式；
            提供一个静态方法，传入name，遍历每一个枚举，执行它的回调函数，如果返回true就返回；都不符合最后返回DEFAULT
        我们的controller中就可以去除掉if else的逻辑使用typeOf传入name来判断业务类型
```java
package com.yaohua.designpattern.策略模式.enums;

import java.util.Arrays;

import java.util.function.Predicate;

public enum BusinessType {

    GZS("GZS"::equals),
    PJH("PJH"::equals),
    RJH("RJH"::equals),
    DEFAULT(name -> true);

    private final Predicate<String> support;

    private BusinessType(Predicate<String> support){
        this.support =support;
    }

    public static BusinessType typeOf(String name){
        return Arrays.stream(values())
                .filter(type -> type != DEFAULT)
                .filter(type -> type.support.test(name))
                .findFirst()
                .orElse(DEFAULT);
    }


}




//controller
@RestController("/test")
public class IdGenerate {


    @Autowired
    private List<IdGenerateService> idGenerateServices;


    @GetMapping("/getId/{name}")
    public  String  getId(@PathVariable("name") String name) throws IllegalAccessException {
        BusinessType businessType = BusinessType.typeOf(name);
        for (IdGenerateService idGenerateService : idGenerateServices) {
            if (idGenerateService.support().equals(businessType)) {
                return idGenerateService.generateId();
            }
        }
        throw new IllegalAccessException(name + "无法找到对应的编码逻辑处理器");
    }

}

```



版本七：解决版本五的第二个问题：我们每一次选择策略都要遍历一次，（解决思路，我们的每一个业务类型，都对应一个策略； 我们把它变成一个MAP）；
        代码如下： 取消掉 list；
                 增加一个Map，key是业务类型，value是支持这个业务类型的策略
                 使用函数注入，遍历所有的策略，转换成一个map key就是当前策略支持的业务类型，value就是当前策略；
                 并且校验 我们的业务类型和策略是否数量一致来增加健壮性；
                 此时我们不再使用List，那我们也不再需要使用Order来定义顺序了，去掉每个策略的@Order注解

```java
@RestController("/test")
public class IdGenerate {
    /**
     *
     * 将自动注入的List 转为Map ，防止每次都要遍历
     *
     * @Autowired private List<IdGenerateService> idGenerateServices;
     **/


    //使用函数注入 来对idGenerateServiceMap进行注入
    private Map<BusinessType, IdGenerateService> idGenerateServiceMap;




    @GetMapping("/getId/{name}")
    public String getId(@PathVariable("name") String name) throws IllegalAccessException {
        BusinessType businessType = BusinessType.typeOf(name);
        IdGenerateService idGenerateService = idGenerateServiceMap.get(businessType);
        return  idGenerateService.generateId();

    }

    //只有注入的时候遍历一次List ,map的key是它支持的业务类型，value就是它自己；
    @Autowired
    public void setIdGenerateServiceMap(List<IdGenerateService> idGenerateServices) throws IllegalAccessException {
        this.idGenerateServiceMap = idGenerateServices.stream().collect(Collectors.toMap(IdGenerateService::support, Function.identity()));
        if (this.idGenerateServiceMap.size() != BusinessType.values().length){
            throw new IllegalAccessException("有业务类型找不到对应的策略");
        }
    }

}

```


版本八： 我们的IdGenerateService结构是一个生成id的策略；应该只有一个生成id的策略逻辑；你不需要告诉我你支持什么业务类型 
        移除support函数，用注解来实现
        创建一个注解，value就是值就是BusinessType
        controller的修改：我们在初始化map的时候只要有SupportBusinessType注解的策略；
        并且写一个私有方法，获取当前策略的SupportBusinessType的值；


```java
//自定义一个注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportBusinessType {

    //要传递一个参数，值不许是BusinessType
    BusinessType value();
}

/**
 * ID生成器，判断业务，
 */

@RestController("/test")
public class IdGenerate {
    /**
     *
     * 将自动注入的List 转为Map ，防止每次都要遍历
     *
     * @Autowired private List<IdGenerateService> idGenerateServices;
     **/


    //使用函数注入 来对idGenerateServiceMap进行注入
    private Map<BusinessType, IdGenerateService> idGenerateServiceMap;




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


```