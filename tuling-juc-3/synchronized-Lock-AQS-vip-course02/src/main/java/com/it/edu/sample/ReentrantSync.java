package com.it.edu.sample;

public class ReentrantSync {

    private final static Object object = new Object();

    public static void reentrantlock(){
        synchronized (object) {
            System.out.println("message Info:--->进入第一个同步块");
            synchronized (object){
                System.out.println("message Info:--->进入第二个同步块");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantSync.reentrantlock();
    }

}
