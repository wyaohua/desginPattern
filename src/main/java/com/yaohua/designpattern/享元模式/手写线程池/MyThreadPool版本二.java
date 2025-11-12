package com.yaohua.designpattern.享元模式.手写线程池;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool版本二 {


    //使用阻塞队列来防止 空轮询
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue(1024);
    /**
     * 版本二：
     *     我们将提交的任务都存在阻塞队列；
     *     然后用我们的唯一的一个线程，不停的执行任务
     */

    private Thread thread = new Thread(()->{
        while (true){
            try {
                Runnable take = blockingQueue.take();
                take.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    },"唯一线程");


    {
        //启动线程
        thread.start();
    }

    public void execute(Runnable runnable) {
        /**
         * 这里使用offer ，添加队列成功会有返回值；
         *   add 添加失败会抛出异常；
         */
        boolean offer = blockingQueue.offer(runnable);
    }

}
