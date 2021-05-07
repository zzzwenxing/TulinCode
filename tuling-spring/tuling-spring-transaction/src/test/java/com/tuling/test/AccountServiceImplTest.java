package com.tuling.test;


import com.tuling.service.AccountService;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/8/28.
 */
public class AccountServiceImplTest {


    @org.junit.Test
    public void addAccount() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-tx.xml");
        AccountService service = context.getBean(AccountService.class);
        service.addAccount("luban", 10000);
    }
}
