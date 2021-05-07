package com.it.edu.jmm;

/**
 * @author ：图灵-杨过
 * @date：2019/7/21
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class VolatileBarrierExample {
    int a;
    volatile int m1 = 1;
    volatile int m2 = 2;

    void readAndWrite() {
        int i = m1;   // 第一个volatile读
        int j = m2;   // 第二个volatile读

        a = i + j;    // 普通写

        m1 = i + 1;   // 第一个volatile写
        m2 = j * 2;   // 第二个 volatile写
    }

}
