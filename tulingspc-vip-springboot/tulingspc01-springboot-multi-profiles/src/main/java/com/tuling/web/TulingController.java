package com.tuling.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/5/20.
 */
@RestController
public class TulingController {

    @RequestMapping("/testMultiProfiles")
    public String testMultiProfiles() {
        return "hello tuling";
    }
}
