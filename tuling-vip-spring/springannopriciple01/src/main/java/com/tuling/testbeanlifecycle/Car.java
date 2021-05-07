package com.tuling.testbeanlifecycle;

/**
 * Created by smlz on 2019/5/19.
 */
public class Car {

    public Car() {
        System.out.println("Car的构造方法..........");
    }


    public void init() {
        System.out.println("Car的初始化方法......init");
    }

    public void destroy() {
        System.out.println("Car的销毁方法.....destroy");
    }


}
