package com.tuling.testautowired.autowiredinmethod;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/24.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        TulingAspect tulingAspect =ctx.getBean(TulingAspect.class);
        System.out.println(tulingAspect.toString());

        Object tulingDao = ctx.getBean(TulingLog.class);
        System.out.println(tulingDao.toString());
    }
}
