package com.it.edu.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: 图灵学院-杨过
 * QQ：692927914
 * @date: 2019-04-14 21:28
 */
public class MapResizer implements Runnable {
    private static Map<Integer,Integer> map = new HashMap<Integer, Integer>(2);

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public void run() {
        while(atomicInteger.get() < 100000){
            map.put(atomicInteger.get(),atomicInteger.get());
            atomicInteger.incrementAndGet();
        }
    }
}
