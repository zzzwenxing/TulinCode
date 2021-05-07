package com.tuling.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/4/26.
 */
@RestController
public class ConfigController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${spring.profiles}")
    private String profiles;

    @RequestMapping("/getConfig4Remote")
    public Map<String,Object> getConfig4Remote() {
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("applicationName",applicationName);
        retMap.put("port",serverPort);
        retMap.put("profiles",profiles);
        return retMap;
    }
}
