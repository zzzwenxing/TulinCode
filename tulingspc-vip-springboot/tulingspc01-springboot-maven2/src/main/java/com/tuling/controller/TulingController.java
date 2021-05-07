package com.tuling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by smlz on 2019/7/12.
 */
@RestController
public class TulingController {

    @RequestMapping("/testHello")
    public String hello() {
        System.out.println("hello tuling");
        return "hello tuling";
    }
}
