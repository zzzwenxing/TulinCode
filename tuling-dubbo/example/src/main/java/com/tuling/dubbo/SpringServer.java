package com.tuling.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Tommy
 * Created by Tommy on 2019/12/4
 **/
public class SpringServer {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("provide.xml");
        System.out.println("服务已暴露");
        System.in.read();
    }
}
