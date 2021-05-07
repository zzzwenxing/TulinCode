package com.tuling.testscope;

import com.tuling.testscope.compent.Person;
import com.tuling.testscope.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/20.
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) ctx.getBean("person");
        Person person2 = (Person) ctx.getBean("person");
        System.out.println(person==person2);
    }
}
