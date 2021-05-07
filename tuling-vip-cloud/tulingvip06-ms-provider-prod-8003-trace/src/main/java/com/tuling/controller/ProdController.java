package com.tuling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/5/6.
 */
@RestController
@RequestMapping("/prod")
@Slf4j
public class ProdController {

    @RequestMapping("/queryProdInfoById/{prodId}")
    public Map<String,Object> queryProdInfoById(@PathVariable("prodId") Integer prodId) throws InterruptedException {
/*        if(prodId==1) {
            throw new RuntimeException("人为抛出异常");
        }*/
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("prodId",prodId);
        retMap.put("prodName","iphone 100");
        return retMap;
    }
}
