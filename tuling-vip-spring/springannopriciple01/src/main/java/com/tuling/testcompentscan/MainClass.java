package com.tuling.testcompentscan;

import com.tuling.testcompentscan.config.MainConfig;
import com.tuling.testcompentscan.controller.TulingController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/19.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinationNames = ctx.getBeanDefinitionNames();
        for (String name:beanDefinationNames) {
            System.out.println("bean的定义信息:"+name);
        }

    }
}
