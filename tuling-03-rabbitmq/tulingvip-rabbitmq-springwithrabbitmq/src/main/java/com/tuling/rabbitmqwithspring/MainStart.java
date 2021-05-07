package com.tuling.rabbitmqwithspring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/10/8.
 */
public class MainStart {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitmqConfig.class);
    }
}
