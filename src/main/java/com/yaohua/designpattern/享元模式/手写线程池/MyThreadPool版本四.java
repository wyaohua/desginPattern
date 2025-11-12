package com.yaohua.designpattern.享元模式.手写线程池;


import com.yaohua.designpattern.享元模式.手写线程池.拒绝策略.RejectHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 将版本三的代码进行封装
 */
public class MyThreadPool版本四 {

    //使用阻塞队列来防止 空轮询
    public final BlockingQueue<Runnable> blockingQueue ;
    //核心线程数量
    private final int corePoolSize;
    //最大线程数
    private final int maxSize ;
    //非核心线程等待的时间
    private final int timeOut;
    //非核心线程等待的时间单位
    private final TimeUnit timeUnit;
    //拒绝策略
    private final RejectHandle rejectHandle;


    public MyThreadPool版本四(int corePoolSize , int maxSize , int timeOut , TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectHandle rejectHandle){
        this.corePoolSize=  corePoolSize;
        this.maxSize = maxSize;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.blockingQueue = blockingQueue;
        this.rejectHandle = rejectHandle;
    }

    //核心线程集合
    List<Thread> coreList = new ArrayList<>();

    //辅助线程集合
    List<Thread> supportList = new ArrayList<>();




    public void execute(Runnable runnable) {
        //先创建好核心线程
        if (coreList.size() <=10){
            Thread thread = new CoreThread();
            coreList.add(thread);
            thread.start();
        }

        //提交任务成功直接返回
        if (blockingQueue.offer(runnable)) {
            return;
        }
        //提交任务失败，创建辅助线程
        if (coreList.size() + supportList.size() <= maxSize){
              Thread thread = new SupportThread();
              supportList.add(thread);
              thread.start();
        }
        //将提交失败的任务重新加入阻塞队列
        if (!blockingQueue.offer(runnable)){
            rejectHandle.reject(runnable,this);
        }

    }


    //核心线程和执行的任务
    class CoreThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    Runnable take = blockingQueue.take();
                    take.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    //辅助线程和要执行任务
    class SupportThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    Runnable take = blockingQueue.poll(timeOut, timeUnit);
                    if (take == null){
                        break;
                    }
                    take.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("非核心线程 结束了生命！"+Thread.currentThread().getName());
        }
    }

}
