package com.tuling.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/5/20.
 */
@RestController
public class TulingController {

    @RequestMapping("/testBoot4War")
    public String testQksByIdea() {
        return "hello tuling testBoot4War";
    }
}
