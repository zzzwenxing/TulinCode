package com.it.edu.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 自定义一个箱子
 * @author: 图灵学院-杨过
 * QQ：692927914
 */
public class ArrayBlockingQueueTest {
    /**
     * 创建容量大小为1的有界队列
     */
    private BlockingQueue<Ball> blockingQueue = new ArrayBlockingQueue<Ball>(1);

    /**
     * 队列大小
     * @return
     */
    public int queueSize(){
        return blockingQueue.size();
    }

    /**
     * 将球放入队列当中,生产者
     * @param ball
     * @throws InterruptedException
     */
    public void produce(Ball ball) throws InterruptedException{
        blockingQueue.put(ball);
    }

    /**
     * 将球从队列当中拿出去，消费者
     * @return
     */
    public Ball consume() throws InterruptedException {
       return blockingQueue.take();
    }

    public static void main(String[] args){
        final ArrayBlockingQueueTest box = new ArrayBlockingQueueTest();
        ExecutorService executorService = Executors.newCachedThreadPool();

        /**
         * 往箱子里面放入乒乓球
         */
        executorService.submit(new Runnable() {
            public void run() {
                int i = 0;
                while (true){
                    Ball ball = new Ball();
                    ball.setNumber("乒乓球编号:"+i);
                    ball.setColor("yellow");
                    try {
                        System.out.println(System.currentTimeMillis()+
                                ":准备往箱子里放入乒乓球:--->"+ball.getNumber());
                        box.produce(ball);
                        System.out.println(System.currentTimeMillis()+
                                ":往箱子里放入乒乓球:--->"+ball.getNumber());
                        System.out.println("put操作后，当前箱子中共有乒乓球:--->"
                                + box.queueSize() + "个");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        });

        /**
         * consumer，负责从箱子里面拿球出来
         */
        executorService.submit(new Runnable() {
            public void run() {
                while (true){
                    try {
                        System.out.println(System.currentTimeMillis()+
                                "准备到箱子中拿乒乓球:--->");
                        Ball ball = box.consume();
                        System.out.println(System.currentTimeMillis()+
                                "拿到箱子中的乒乓球:--->"+ball.getNumber());
                        System.out.println("take操作后，当前箱子中共有乒乓球:--->"
                                + box.queueSize() + "个");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

}
