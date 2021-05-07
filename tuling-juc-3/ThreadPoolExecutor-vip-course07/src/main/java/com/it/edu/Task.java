package com.it.edu;

/**
 * @author ：图灵-杨过
 * @date：2019/7/25
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class Task implements Runnable {
    private int nov;

    public Task(int i){
        this.nov = i;
    }
    public void run() {
        System.out.println("执行当前任务的线程是："+Thread.currentThread().getName());
        System.out.println("我是任务:"+nov+"我在执行...");
    }

}
