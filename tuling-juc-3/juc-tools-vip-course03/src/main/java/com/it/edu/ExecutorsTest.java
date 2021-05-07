package com.it.edu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：图灵-杨过
 * @date：2019/7/15
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class ExecutorsTest {



    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

        //延迟三秒执行
        es.schedule(new Runnable() {
            public void run() {
                System.out.println("我在跑......");
            }
        },3, TimeUnit.SECONDS);

        es.shutdown();

    }

}
