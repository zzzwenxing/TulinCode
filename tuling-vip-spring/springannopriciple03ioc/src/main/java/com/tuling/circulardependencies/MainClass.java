package com.tuling.circulardependencies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/29.
 */
public class MainClass {

    public static void main(String[] args) {
        //创建IOC容器
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

        //去容器的缓存中直接拿
        InstanceA instanceA = ctx.getBean(InstanceA.class);
        ctx.close();
    }
}
