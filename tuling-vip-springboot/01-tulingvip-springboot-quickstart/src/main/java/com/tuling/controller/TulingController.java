package com.tuling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于idea快速构建的工程
 * Created by smlz on 2019/3/19.
 */
@RestController
public class TulingController {

    @Autowired
    private Person person;

    @RequestMapping("/helloTuling")
    public String helloTuling() {
        System.out.println(person.toString());
        return "tuling->司马是一个老贼";
    }
}
