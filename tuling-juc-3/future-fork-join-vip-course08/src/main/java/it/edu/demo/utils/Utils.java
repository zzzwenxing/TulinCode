package it.edu.demo.utils;

import java.util.Random;

/**
 * @author     ：杨过
 * @date       ：Created in 2019/7/30 14:23
 * @description：天下风云出我辈，一入代码岁月催
 * @modified By：
 * @version:  V1.0
 */
public class Utils {

    public static int[] buildRandomIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

    public static int[] buildRandomIntArray() {
        int size = new Random().nextInt(100);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] ints = Utils.buildRandomIntArray(20000);

        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}