package com.tuling.busi;

import com.tuling.busi.service.PayService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/7/3.
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        PayService payService = annotationConfigApplicationContext.getBean(PayService.class);
        payService.pay("123456789",10);
    }
}
