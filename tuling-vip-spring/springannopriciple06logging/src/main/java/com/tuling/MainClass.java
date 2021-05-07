package com.tuling;

import com.tuling.config.MainConfig;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;




/**
 * Created by smlz on 2019/6/24.
 */
public class MainClass {

    private static Logger logger = Logger.getLogger(MainClass.class.getName());

    //private static Logger logger = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

        logger.info("hello tuling");

        ctx.start();
    }
}
