package com.yaohua.designpattern.享元模式.手写线程池.拒绝策略;

import com.yaohua.designpattern.享元模式.手写线程池.MyThreadPool版本四;


/**
 * 这个策略是队列中扔一个，加入新的任务；
 */
public class DisCardRejectHandle implements RejectHandle{
    @Override
    public void reject(Runnable rejectCommon, MyThreadPool版本四 myThreadPool) {
        myThreadPool.blockingQueue.poll();
        myThreadPool.execute(rejectCommon);
    }
}
