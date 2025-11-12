package com.yaohua.designpattern.享元模式.手写线程池;


import java.util.ArrayList;
import java.util.List;

public class MyThreadPool版本一 {

    /**
     * 版本一：
     *  存在一些问题：（线程不可复用）
     *    1. 我们既然用了线程池，是为了避免频繁创建线程，但是显然我们的版本一没有做到这一点
     *    2. 我们没办法管理我们创建的线程，我们虽然将我们创建的线程加入到了一个集合，但是实际上线程执行完，就会自动销毁；
     */

    List<Runnable> runnables = new ArrayList<>();
    public void execute(Runnable runnable) {
        Thread thread = new Thread(runnable);
        runnables.add(thread);
        thread.start();
    }
}
