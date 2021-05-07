package com.tuling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/3/24.
 */
@RestController
public class TulingController {

    @RequestMapping("/tulingHello")
    public String tulingHello() {
        return "ok";
    }
}
