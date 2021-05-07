package com.tuling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by smlz on 2019/3/19.
 */
@RestController
public class TulingController {

    @RequestMapping("/testController")
    public String helloTuling() {
        return "hello!tuling";
    }
}
