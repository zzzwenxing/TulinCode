package com.tuling.testconditional;

import com.tuling.testconditional.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/20.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        for(String beanName:ctx.getBeanDefinitionNames()) {
            System.out.println("beanName:"+beanName);
        }

    }
}
