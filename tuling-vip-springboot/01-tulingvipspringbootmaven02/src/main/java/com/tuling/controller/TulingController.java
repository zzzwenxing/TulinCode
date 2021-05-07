package com.tuling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/3/29.
 */
@RestController
public class TulingController {

    @RequestMapping("/testSpringboot")
    public String test() {
        System.out.println("OK");
        return "OK";
    }
}
