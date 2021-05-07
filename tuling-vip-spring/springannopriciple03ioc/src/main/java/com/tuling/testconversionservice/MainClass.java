package com.tuling.testconversionservice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.util.Date;

/**
 * Created by smlz on 2019/6/3.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        ConversionServiceFactoryBean conversionServiceFactoryBean = (ConversionServiceFactoryBean) ctx.getBean(ConversionServiceFactoryBean.class);
        //System.out.println(conversionServiceFactoryBean.getObjec("2019-06-03 12:00:00"));

        System.out.println(conversionServiceFactoryBean.getObject().convert("2019-06-03 12:00:00",Date.class));
    }
}
