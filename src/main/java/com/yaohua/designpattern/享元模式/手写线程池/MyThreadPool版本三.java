package com.yaohua.designpattern.享元模式.手写线程池;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyThreadPool版本三 {

    public MyThreadPool版本三(int corePoolSize ,int maxSize ,int timeOut , TimeUnit timeUnit){
        this.corePoolSize=  corePoolSize;
        this.maxSize = maxSize;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
    }
    //使用阻塞队列来防止 空轮询
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue(1024);

    //将每个线程要做的事情提取出来
    private Runnable coreTask =()->{
        while (true){
            try {
                Runnable take = blockingQueue.take();
                take.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    /**
     * 非核心线程，等待1秒未等待到任务，就结束掉核心线程, 阻塞队列使用poll()方法；
     */
    private Runnable supportTask =()->{
        while (true){
            try {
                Runnable take = blockingQueue.poll(1, TimeUnit.SECONDS);
                if (take == null){
                    break;
                }
                take.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("非核心线程 结束了生命！"+Thread.currentThread().getName());
    };


    /**
     * 核心线程数量
     */
    private int corePoolSize = 10;


    //最大线程数
    private int maxSize =16;


    //核心线程集合
    List<Thread> coreList = new ArrayList<>();

    //辅助线程集合
    List<Thread> supportList = new ArrayList<>();


    //非核心线程等待的时间
    private  int timeOut=1;


    //非核心线程等待的时间单位
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public void execute(Runnable runnable) {
        //先创建好核心线程
        if (coreList.size() <=10){
            Thread thread = new Thread(coreTask);
            coreList.add(thread);
            thread.start();
        }

        //提交任务成功直接返回
        if (blockingQueue.offer(runnable)) {
            return;
        }
        //提交任务失败，创建辅助线程
        if (coreList.size() + supportList.size() <= maxSize){
              Thread thread = new Thread(supportTask);
              supportList.add(thread);
              thread.start();
        }
        //将提交失败的任务重新加入阻塞队列
        if (!blockingQueue.offer(runnable)){
            throw new RuntimeException("阻塞队列满了");
        }

    }



}
