package com.tuling.testlookup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Created by smlz on 2019/6/6.
 */
@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Car {

    public Car() {
        System.out.println("car的构造方法");
    }


}
