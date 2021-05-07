package com.zhuge.credit.controller;

import com.zhuge.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    private CreditService creditService;

    @PostMapping(value = "/add/{userId}/{creditCount}")
    public String addCredit(@PathVariable("userId") Long userId,
                            @PathVariable("creditCount") Integer creditCount) {
        return creditService.addCredit(userId, creditCount);
    }
}
