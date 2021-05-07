package com.tuling.testbeanpostpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/31.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        TulingLog tulingLog = context.getBean(TulingLog.class);
        tulingLog.print();

        TulingAspect tulingAspect = context.getBean(TulingAspect.class);
        tulingAspect.invokeTulingLogPrint();

        context.close();
    }
}
