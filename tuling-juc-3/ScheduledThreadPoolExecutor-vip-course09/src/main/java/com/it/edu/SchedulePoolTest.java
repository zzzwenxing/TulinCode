package com.it.edu;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ：图灵-杨过
 * @date：2019/8/5
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class SchedulePoolTest {

    private static Logger logger = Logger.getLogger(SchedulePoolTest.class);

    private static final AtomicLong l = new AtomicLong(0) ;

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(10);


    public static void main(String[] args) {

		/**
		 * 使用scheduleAtFixedRate ， 该方法第三个参数表示在上一个个任务开始执行之后延迟
		 * 多少秒之后再执行， 是从上一个任务开始时开始计算
		 * 但是还是会等上一个任务执行完之后，下一个任务才开始执行，最后的结果，就是感觉延迟失去
		 * 了作用
		 *  */
        ScheduledFuture<?> sf1 = scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                long i =  l.getAndAdd(1) ;
                logger.info("start " + i);
                long start = System.currentTimeMillis();
                long end;
                do{
                    end = System.currentTimeMillis();
                }while((end-start) < 5000);
                logger.info("end " + i);
            }
        }, 0, 1 , TimeUnit.SECONDS) ;

		/**
		 * 使用scheduleWithFixedDelay， 该方法第三个参数表示在上一个个任务结束执行之后延迟
		 * 多少秒之后再执行， 是从上一个任务结束时开始计算
		 *  */
        /*ScheduledFuture<?> sf2 = scheduler.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                long i =  l.getAndAdd(1) ;
                System.out.println("start " + i);
                try {
                    TimeUnit.SECONDS.sleep(3) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end " + i);
            }
        }, 0, 2, TimeUnit.SECONDS) ;*/

    }

}


