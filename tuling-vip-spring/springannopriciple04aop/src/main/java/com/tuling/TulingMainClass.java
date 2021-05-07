package com.tuling;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/6/10.
 */
public class TulingMainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Calculate calculate = (Calculate) ctx.getBean("calculate");
        int retVal = calculate.add(2,4);
        System.out.println("运算结果是:"+retVal);
        System.out.println(ctx.getBean("tulingLogAspect"));
    }
}
