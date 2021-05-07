package com.tuling.testalias;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/6/4.
 */
public class MainStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainClass.class);
        System.out.println(ctx.getBean("aliasBean"));
        System.out.println(ctx.getBean("aliasBean2"));

    }
}
