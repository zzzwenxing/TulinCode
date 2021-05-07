package com.it.edu.thread;

import java.util.Arrays;

public class DeadThread {
    private final static String resource_a = "A";
    private final static String resource_b = "B";

    public static void deadLock() {
        Thread threadA = new Thread(()->{
            synchronized (resource_a) {
                System.out.println("get resource a");
                try {
                    Thread.sleep(2000);
                    synchronized (resource_b) {
                        System.out.println("get resource b");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(()->{
            synchronized (resource_b) {
                System.out.println("get resource b");
                synchronized (resource_a) {
                    System.out.println("get resource a");
                }
            }
        });

        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) {
        deadLock();
        String[] a = {"a","dd","g"};
        Arrays.sort(a);
        for (String s : a) {
            System.out.println(s);
        }
//        Character.toLowerCase()

    }

}
