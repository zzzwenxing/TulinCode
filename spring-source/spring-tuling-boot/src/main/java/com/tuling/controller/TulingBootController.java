package com.tuling.controller;

import com.tuling.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/8/18.
 */
@RestController
public class TulingBootController {

    @RequestMapping("/hello")
    public String hello () {
        return "hello";
    }

    @RequestMapping("/tuling")
    public String testTuling() {
        return "tuling";
    }

    @RequestMapping("/getUser")
    public User getUser(@RequestBody User user) {
		return user;
	}



    @RequestMapping("/returnJson")
    public Object returnJson() {
    	Map<String,String> retMap = new HashMap<>();
    	retMap.put("name","张三");
    	return retMap;
	}
}
