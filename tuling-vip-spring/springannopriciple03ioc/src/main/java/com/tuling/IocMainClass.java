package com.tuling;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/29.
 */
public class IocMainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext tcx = new TulingApplicationContext(MainConfig.class);
        //tcx.getEnvironment().getSystemEnvironment().put("os.tuling","hello");
        tcx.getBean("tulingLog");


    }
}
