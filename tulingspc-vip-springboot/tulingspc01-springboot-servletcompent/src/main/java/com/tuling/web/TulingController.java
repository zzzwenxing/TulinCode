package com.tuling.web;

import com.tuling.anno.AngleLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/5/21.
 */
@RestController
public class TulingController {

    @RequestMapping("/testTuling")
    @AngleLogger
    public String tulingHello(String name) {
        System.out.println("name:"+name);
        return "OK";
    }
}
