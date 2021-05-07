package com.tuling.testlookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/6/6.
 */
@Component
public class Boos {

    @Autowired
    private Car car;


    @Override
    public String toString() {
        return "Boos{" +
                "car=" + car +
                '}';
    }

    @Lookup
    public Car getCar() {
        System.out.println("我要从容器中获取");
        return null;
        //return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
