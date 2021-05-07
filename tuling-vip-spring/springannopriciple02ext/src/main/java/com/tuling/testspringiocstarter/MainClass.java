package com.tuling.testspringiocstarter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/6/12.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
       // System.out.println(ctx.getBean(TulingDao.class));
       // System.out.println(ctx.getBean(TulingDao.class));
    }
}
