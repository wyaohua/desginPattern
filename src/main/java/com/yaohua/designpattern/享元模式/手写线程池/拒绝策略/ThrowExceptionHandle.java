package com.yaohua.designpattern.享元模式.手写线程池.拒绝策略;

import com.yaohua.designpattern.享元模式.手写线程池.MyThreadPool版本四;

/**
 * 这个策略是 直接抛出异常
 */
public class ThrowExceptionHandle implements RejectHandle{
    @Override
    public void reject(Runnable rejectCommon, MyThreadPool版本四 myThreadPool) {
        throw new RuntimeException("阻塞队列已经满了");
    }
}
