package com.it.edu.sample;

import java.util.Vector;

public class Biaslock {

    public static Vector<Integer> vector = new Vector<Integer>();

    /**
     * 默认开启偏向锁
     * 开启偏向锁：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
     * 关闭偏向锁：-XX:-UseBiasedLocking
     * @param args
     */
    public static void main(String[] args){
        long begin = System.currentTimeMillis();
        int count = 0;
        int num = 0;
        while(count < 10000000){
            vector.add(num);
            num = num + 5;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}
