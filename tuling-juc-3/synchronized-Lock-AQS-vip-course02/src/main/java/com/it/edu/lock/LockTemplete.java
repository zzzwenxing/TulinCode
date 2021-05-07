package com.it.edu.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockTemplete {
    private Integer counter = 0;
    /**
     * 可重入锁,公平锁
     * 公平锁，
     * 非公平锁
     * 需要保证多个线程使用的是同一个锁
     *
     *
     * synchronized是否可重入？
     * 虚拟机，在ObjectMonitor.hpp定义了synchronized他怎么取重入加锁 ..。hotspot源码
     * counter +1
     * 基于AQS 去实现加锁与解锁
     */
    private ReentrantLock lock = new ReentrantLock(true);

    /**
     * 需要保证多个线程使用的是同一个ReentrantLock对象
     * @return
     */
    public void modifyResources(String threadName){
        System.out.println("通知《管理员》线程:--->"+threadName+"准备打水");
        //默认创建的是独占锁，排它锁；同一时刻读或者写只允许一个线程获取锁
        lock.lock();
            System.out.println("线程:--->"+threadName+"第一次加锁");
            counter++;
            System.out.println("线程:"+threadName+"打第"+counter+"桶水");
            //重入该锁,我还有一件事情要做,没做完之前不能把锁资源让出去
            lock.lock();
            System.out.println("线程:--->"+threadName+"第二次加锁");
            counter++;
            System.out.println("线程:"+threadName+"打第"+counter+"桶水");
            lock.unlock();
            System.out.println("线程:"+threadName+"释放一个锁");
        lock.unlock();
        System.out.println("线程:"+threadName+"释放一个锁");
    }


    public static void main(String[] args){
        LockTemplete tp = new LockTemplete();

        new Thread(()->{
            String threadName = Thread.currentThread().getName();
            tp.modifyResources(threadName);
        },"Thread:杨过").start();

        /*new Thread(()->{
            String threadName = Thread.currentThread().getName();
            tp.modifyResources(threadName);
        },"Thread:小龙女").start();*/

    }

}
