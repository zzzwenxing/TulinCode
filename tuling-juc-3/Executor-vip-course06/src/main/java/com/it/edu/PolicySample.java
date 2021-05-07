package com.it.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ：图灵-杨过
 * @date：2019/7/18
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class PolicySample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        Future<String> future = null;
        List<Future<String>> list = new ArrayList<Future<String>>();

        for (int i=0;i<20;i++){
            list.add(pool.submit(new CallTask()));
        }

        if(!list.isEmpty()){
            for (Future<String> f : list){
                System.out.println(f.get());
            }
        }
    }
}


