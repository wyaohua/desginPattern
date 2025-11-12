package com.yaohua.designpattern.享元模式.手写线程池.拒绝策略;

import com.yaohua.designpattern.享元模式.手写线程池.MyThreadPool版本四;

public interface RejectHandle {

    void reject(Runnable rejectCommon, MyThreadPool版本四 myThreadPool);
}
