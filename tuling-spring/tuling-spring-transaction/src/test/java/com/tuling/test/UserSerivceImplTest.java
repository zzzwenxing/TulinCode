package com.tuling.test;

import com.tuling.service.AccountService;
import com.tuling.service.UserSerivce;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/8/29.
 */
public class UserSerivceImplTest {
    @Test
    public void createUser() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-tx.xml");
        UserSerivce service = context.getBean(UserSerivce.class);
        service.createUser("luban");
    }

}