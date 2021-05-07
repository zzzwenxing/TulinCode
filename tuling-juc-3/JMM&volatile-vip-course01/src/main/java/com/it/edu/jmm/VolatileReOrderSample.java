package com.it.edu.jmm;

import com.it.edu.util.UnsafeInstance;

/**
 * @author     ：
 * @date       ：Created in 2019/6/24 21:36
 * @description：天下风云出我辈，一入代码岁月催
 *
 * 并发场景下存在指令重排
 */
public class VolatileReOrderSample {
    private static int x = 0, y = 0;
    private static int a = 0, b =0;
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        int i = 0;

        for (;;){
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    //由于线程one先启动，下面这句话让它等一等线程two. 读着可根据自己电脑的实际性能适当调整等待时间.
                    shortWait(10000);
                    a = 1; //是读还是写？store，volatile写
                    //storeload ,读写屏障，不允许volatile写与第二部volatile读发生重排
                    //手动加内存屏障
                    UnsafeInstance.reflectGetUnsafe().storeFence();
                    x = b; // 读还是写？读写都有，先读volatile，写普通变量
                    //分两步进行，第一步先volatile读，第二步再普通写
                }
            });
            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    UnsafeInstance.reflectGetUnsafe().storeFence();
                    y = a;
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            /**
             * cpu或者jit对我们的代码进行了指令重排？
             * 1,1
             * 0,1
             * 1,0
             * 0,0
             */
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if(x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }

    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }

}
