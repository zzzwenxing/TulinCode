package com.tuling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/3/29.
 */
@RestController
public class TestTulingController {

    @RequestMapping("/testSpringboot")
    public String toTest() {
        return "司马是一个老贼";
    }
}
