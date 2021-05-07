package com.tuling.web;

import com.tuling.exception.TulingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/5/21.
 */
@RestController
public class TulingController {

    @RequestMapping("/testTuling")
    public String tulingHello() {
        return "OK";
    }

    @RequestMapping("/testError")
    public String testError() {
        throw new TulingException(-1,"服务器内部错误");
    }
}
