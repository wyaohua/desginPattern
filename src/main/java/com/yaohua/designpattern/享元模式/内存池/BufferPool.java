package com.yaohua.designpattern.享元模式.内存池;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 文件名：BufferPool
 * 作者：huahua
 * 时间：2025/11/16 20:02
 * 描述  内存池
 */
public class BufferPool {

    //总长度
    private final int totalSize;

    //槽长度
    private final int slotSize;

    //剩余的空闲长度
    private int freeSize;

    //双端队列。 可以在头尾进行添加删除；
    private final Deque<ByteBuffer> slotQueue = new ArrayDeque<>();
    private final Deque<Condition> waits = new ArrayDeque<>();

    //可重入锁
    private final Lock lock = new ReentrantLock();




    public BufferPool(int totalSize, int slotSize) {
        this.totalSize = totalSize;
        this.slotSize = slotSize;
        this.freeSize = totalSize;
    }


    /**
     * 申请内存
     */
    public ByteBuffer allocate(int size, long timeout) throws InterruptedException {

        if (size >totalSize || size <=0){
            throw  new RuntimeException("申请容量异常");
        }

        lock.lock();
        try {
            //申请的长度和slot长度一致，并且有空闲的slot ，就返回一个slot
            if (size == slotSize && !slotQueue.isEmpty()) {
                return slotQueue.pollFirst();
            }

            //判断 剩余的所有容量是否满足 申请的长度
            if ((freeSize + slotQueue.size() * slotSize) >= size) {
                freeUp(size);
                freeSize -= size;
                return ByteBuffer.allocate(size);
            }

            //如果不满足就等待有人释放了内存
            Condition condition = lock.newCondition();
            waits.addLast(condition);
            //计算等待时间
            long remainTime = timeout;

            try {
                while (true){
                    long start = System.currentTimeMillis();

                    //返回值是true代表被唤醒； 返回值false代表超时醒来
                    boolean wakeup = condition.await(remainTime, TimeUnit.MILLISECONDS);
                    if (!wakeup){
                        throw new RuntimeException("规定时间内未申请到内存");
                    }

                    //申请的长度和slot长度一致，并且有空闲的slot ，就返回一个slot
                    if (size == slotSize && !slotQueue.isEmpty()) {
                        return slotQueue.pollFirst();
                    }
                    //判断 剩余的所有容量是否满足 申请的长度
                    if ((freeSize + slotQueue.size() * slotSize) >= size) {
                        freeUp(size);
                        freeSize -= size;
                        return ByteBuffer.allocate(size);
                    }
                    //需要重新等待，计算的等待时间；
                    remainTime -= (System.currentTimeMillis() - start);

                }
            }finally {
                waits.remove(condition);
            }



        } finally {
            //当前线程释放了锁，就唤醒一个等待的线程
            if (!waits.isEmpty() && !(freeSize == 0 && slotQueue.isEmpty())){
                waits.peekFirst().signal();
            }

            lock.unlock();
        }

    }


    /**
     * 在保证满足申请长度的前提下， 然后保证free满足申请长度
     */
    private void freeUp(int size) {
        while (freeSize < size && !slotQueue.isEmpty()) {
            freeSize += slotQueue.pollFirst().capacity();
        }
    }


    /**
     * 还内存
     */
    public void deallocate(ByteBuffer byteBuffer) {
        lock.lock();
        try {
            //还回来的长度等于一个slot的长度，就直接还到slotQueue中缓存
            if (byteBuffer.capacity() == slotSize) {
                slotQueue.addLast(byteBuffer);
            }else{
                freeSize +=byteBuffer.capacity();
            }
            if (!waits.isEmpty()){
                waits.pollFirst().signal();
            }
        }finally {

            lock.unlock();
        }

    }
}
