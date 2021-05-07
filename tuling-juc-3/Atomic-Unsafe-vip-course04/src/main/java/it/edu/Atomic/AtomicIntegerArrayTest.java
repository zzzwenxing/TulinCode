package it.edu.Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author ：图灵-杨过
 * @date：2019/7/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class AtomicIntegerArrayTest {
    static int[] value = new int[]{1,2};
    static AtomicIntegerArray aiArray = new AtomicIntegerArray(value);


    public static void main(String[] args) {
        aiArray.getAndSet(0,3);
        System.out.println(aiArray.get(0));
        System.out.println(value[0]);
        if(aiArray.get(0) != value[0]){
            System.out.println("是否相等");
        }
    }

}
