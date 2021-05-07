package com.it.edu;

/**
 * @author ：图灵-杨过
 * @date：2019/7/18
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class RunTask implements Runnable {

    public void run() {
        System.out.println("Thread name:"+Thread.currentThread().getName());
    }
}
