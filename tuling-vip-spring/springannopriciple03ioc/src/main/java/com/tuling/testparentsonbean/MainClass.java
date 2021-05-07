package com.tuling.testparentsonbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/6/3.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        TulingSonCompent tulingSonCompent = ctx.getBean(TulingSonCompent.class);
        tulingSonCompent.print();
        System.out.println(ctx.getBean("tulingParentCompent"));
    }
}
