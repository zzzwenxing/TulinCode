package com.tuling.controller;

import com.tuling.producter.TulingProductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/3/24.
 */
@RestController
public class TestMqController {

    @Autowired
    private TulingProductor tulingProductor;
    @RequestMapping("/testMqSender/{msg}")
    public String testMqSender(@PathVariable("msg") String msg) {
        tulingProductor.sendMsg(msg);
        return "OK";
    }

    @RequestMapping("/testFanoutMqSender/{msg}")
    public String testFanoutMqSender(@PathVariable("msg") String msg) {
        tulingProductor.sendMsg2Fanout(msg);
        return "OK";
    }

    @RequestMapping("/testTopicSender/{msg}")
    public String testTopicSender(@PathVariable("msg") String msg) {
        tulingProductor.sendMsg2Topic(msg);
        return "OK";
    }
}
