package com.yaohua.designpattern.享元模式.手写线程池;



import com.yaohua.designpattern.享元模式.手写线程池.拒绝策略.ThrowExceptionHandle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 享元模式 是一种池化技术；
 * 使用场景：
 *     1. 系统存在频繁使用的小对象 （Integer类的cache）
 *     2. 对象创建的代价高 （线程池）
 *     3. 频繁使用的胸痛对象 （连接池）
 */
public class Main {

    public static void main(String[] args) {
        MyThreadPool版本四 myThreadPool = new MyThreadPool版本四(2,4,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(2),new ThrowExceptionHandle());

        myThreadPool.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        System.out.println("主线程");
    }

}
