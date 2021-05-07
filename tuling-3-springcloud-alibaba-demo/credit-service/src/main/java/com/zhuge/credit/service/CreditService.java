package com.zhuge.credit.service;

import org.springframework.stereotype.Service;

@Service
public class CreditService {

    public String addCredit(Long userId, Integer creditCount) {
        System.out.println("用户id=" + userId + "：增加积分" + creditCount);
        return "success";
    }

}