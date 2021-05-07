package com.tuling.testbeanlifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by smlz on 2019/5/24.
 */
//@Component
public class Book {

    public Book() {
        System.out.println("book 的构造方法");
    }

    @PostConstruct
    public void init() {
        System.out.println("book 的PostConstruct标志的方法");
    }

    @PreDestroy
    public void destory() {
        System.out.println("book 的PreDestory标注的方法");
    }
}
