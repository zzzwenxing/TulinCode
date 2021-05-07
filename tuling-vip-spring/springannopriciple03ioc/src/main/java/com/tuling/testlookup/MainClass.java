package com.tuling.testlookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/6/6.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

        Boos boos =ctx.getBean(Boos.class);
        System.out.println(boos.getCar());
        System.out.println(ctx.getBean(Boos.class).getCar());

    }
}
