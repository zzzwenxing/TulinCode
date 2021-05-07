package com.it.edu;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：图灵-杨过
 * @date：2019/8/7
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class ThreadTest {
    public static void main(String[] args) {
        //创建有返回值的任务
        FutureTask ft = new FutureTask<String>(new CallTask());
        Thread t = new Thread(ft);
        t.start();
        try {
            System.out.println("out put:"+ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
