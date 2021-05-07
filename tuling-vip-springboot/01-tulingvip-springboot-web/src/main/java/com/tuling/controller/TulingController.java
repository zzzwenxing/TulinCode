package com.tuling.controller;

import com.tuling.exception.TulingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/3/19.
 */
@RestController
public class TulingController {

    @RequestMapping("/testTuling")
    public String testTuling() {
        return "tuling";
    }

    @RequestMapping("/testError")
    public String testError() {
        throw new TulingException(100,"用户不存在");
    }
}
