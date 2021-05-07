package com.it.edu;

import java.util.concurrent.*;

/**
 * @author ：图灵-杨过
 * @date：2019/7/18
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class ExecutorSample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //ThreadPoolExecutor 这样的线程池类
        for (int i=0;i<20;i++){
            executor.execute(new RunTask());
            executor.submit(new RunTask());
        }
        //验证线程创建过程比较慢，好资源
        executor.shutdownNow();//执行该方法进入stop状态
        executor.execute(new RunTask());
    }

}
