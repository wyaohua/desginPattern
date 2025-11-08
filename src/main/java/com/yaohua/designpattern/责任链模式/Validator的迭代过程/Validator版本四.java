package com.yaohua.designpattern.责任链模式.Validator的迭代过程;

import com.yaohua.designpattern.责任链模式.异常.ValidatoeException;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器实现.LengthValidator;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器实现.MaxValidator;
import com.yaohua.designpattern.责任链模式.校验逻辑.对应validator版本四.校验器实现.MinValidator;
import com.yaohua.designpattern.责任链模式.注解.Length;
import com.yaohua.designpattern.责任链模式.注解.Max;
import com.yaohua.designpattern.责任链模式.注解.Min;
import com.yaohua.designpattern.责任链模式.链条.ValidatorChain对应版本四;


import java.lang.reflect.Field;

/**
 * 文件名：Validator
 * 作者：huahua
 * 时间：2025/11/9 01:53
 * 描述
 */
public class Validator版本四 {




    /**
     * 一：版本四：所有的校验器都执行，将所有未通过校验的信息一次性暴露给用户；带着这个功能来带到版本四；
     *
     * 二：思考：目前我们因为直接抛出异常 所以是没办法实现的；
     *
     * 三:实现功能；
     *  1. 我们或许校验链的 validate()将我们的异常捕获 然后存储到 集合中；最后判断集合不为空 再一起抛出异常；  (pass的实现方案)
     *       1.1 这样的实现逻辑，是通过异常捕获来进行逻辑的判断。是不符合规范的；
     *
     *
     *  2. 换一种实现方式 ，要知道 我们版本三中的ValidatorChain中的链条list，他们中存储的每一个校验器都是独立存在于list中的，都无法感知到其他校验器的存在，也就是说每个校验器都没有个它的上下文；
     *         2.1 我们需要一个上下文对象 ValidatorContext ； 让每个链中的每个校验器都可以拿到这个上下文对象；
     *         2.2 我们把链中的所有控制，都交给这个上下文对象控制，也就是说每个校验器都可以通过这个上下文对象去控制我们的 责任链；对整个流程进行干预；
     *         2.3 就开始改造我们的 ：
     *
功能一： 添加ValidatorContext上下文对象并且实现 收集每个校验器的异常最后抛出
     *              首先 ValidatorHandler 校验器接口的改造 ，参数要有一个 ValidatorContext ，这样每个校验器才能拥有上下文对象；
     *                  我们要让责任链中的所有校验器 不通过的都收集起来，然后一起抛出异常； 那么我们的 校验器就不需要抛出异常了；
     *                  然后我们的 ValidatorHandler 上下文对象提供能够收集异常的功能 addErrorMessage()； 这样每个节点的异常信息都会存储在 ValidatorContext 中；最后遍历完成后，再抛出去；
     *                  然后每个校验器 在抛出异常的地方变为上下文对象收集异常的逻辑；
     *                  最后在   ValidatorContext 提供一个功能，判断集合是否为空，不为空则抛出异常；
注意: 我们的责任链的创建基础是根据 字段；也就是说 目前只有 name 1个校验， age2个校验；  就算3个都不通过， name的单独抛出异常， age的两个合在一起抛出来异常；
     *      除非我们以对象为基础去创建责任链；才能让 name 和age 不同字段的校验异常 合在一起抛出来；
     *
     * 我们说到了 这个上下文对象，能够让责任链的任意节点都能够控制整个链条的流程
功能二： 让节点能够停止责任链；
     *         首先提供功能，  ValidatorContext 添加属性stop作为是否停止责任链的标记 ；提供修改 和 获取stop属性值的功能；
     *         然后 链条对象 ValidatorChain 循环遍历责任链的过程中，每个节点检验之后，判断上下文对象的stop属性值，如果为true，跳出循环；
     *         至此就可以测试了， 例如max比较特殊 不通过校验，就需要直接停止， 在max调用context.stopChain()；
     *
     *
     * 一个完整的责任链，应该有doNext()这个方法，每个节点去控制是否执行下一个节
功能三： 让每个节点能够控制是否继续将责任链进行下去；
     *        首先提供功能：ValidatorContext ，增加属性index ，表示当前节点的索引； doNext()让index++ ；getCurrentIndex() 返回当前节点的索引；
     *        然后链条的每个校验节点都要调用这个 doNext();否则就认为 不再流转；
     *        最后 链条对象ValidatorChain对象，循环遍历责任链的过程中，每个节点检验之后，根据index的值判断，节点是否调用了doNext();没有调用的话就直接break；结束循环；
     *
     * 我们看 TomCat中的Filter 就有一个 doFilter(); 就等同于我们的 doNext(); 但是TomCat的 doFilter()是有参数的，可以在节点之间传递 request和response对象，每个节点都可以进行更改
功能四： 我们也借鉴 doFilter ，让我们的doNext()可以传递数据，然后每个节点都可以修改这个数据；
             首先给ValidatorContext上下文对象的doNext调用需要传参 doNext(object value) ;
             然后每个节点可以通过ValidatorContext 提供的getValue 拿到上个节点传递过来的值， 也可以修改这个值 然后调用doNext传递给下个的节点；
            并且 ValidatorContext提供构造函数 给这个value 提供初始值；
     *
     *
     *
最后 ::::: 其实我们可以在 ValidatorContext 中存储整个链条的任意信息；只需要提供功能，让每个节点调用就可以；这样就让节点之间不再是完全独立地；节点之间可以互相传递新，也可以根据传递的信息进行逻辑处理；

思考题： 实现一个总的上下文，管理不同字段之间的检验连；
     */

    public  void validate(Object  obj) throws ValidatoeException, IllegalAccessException {
        //1.获取类对象 ，为了反射
        Class<?> aClass = obj.getClass();
        //2.遍历对象字段
        for (Field declaredField : aClass.getDeclaredFields()) {
            declaredField.setAccessible(true);

            //3.拿到校验链，然后传入字段的值，进行校验；
           ValidatorChain对应版本四 validatorChain = buildValidateChain( declaredField );
            validatorChain.validate(declaredField.get(obj));
        }

    }

    /**
     * 版本三 拓展出来的函数
     * 根据属性，组件 链对象的函数;
     * 如果满足条件就将对应的校验器添加到链中；
     *
     * 这段代码 还可以优化， 使用工厂模式 或者 享元模式 或者 校验池
     * @param declaredField
     * @return
     */

    private ValidatorChain对应版本四 buildValidateChain(Field declaredField) {
        ValidatorChain对应版本四 validatorChain = new ValidatorChain对应版本四();

        Length length = declaredField.getAnnotation(Length.class);
        if (length != null) {
            validatorChain.addLastHandler(new LengthValidator(length.value()));
        }

        Max max = declaredField.getAnnotation(Max.class);
        if (max != null) {
            validatorChain.addLastHandler(new MaxValidator(max.value()));
        }

        Min min = declaredField.getAnnotation(Min.class);
        if (min != null) {
            validatorChain.addLastHandler(new MinValidator(min.value()));
        }
        return validatorChain;
    }



}
