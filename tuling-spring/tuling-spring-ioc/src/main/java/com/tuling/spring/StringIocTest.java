package com.tuling.spring;/**
 * Created by Administrator on 2018/8/26.
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/26
 **/
public class StringIocTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");
        context.getBean(HelloSpring.class);
      /*  context.getBean("driver");
        context.getBean(java.sql.Driver.class);*/
    }

}
