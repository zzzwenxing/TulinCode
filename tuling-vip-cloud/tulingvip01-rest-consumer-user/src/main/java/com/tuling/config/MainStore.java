package com.tuling.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smlz on 2019/3/27.
 */
public class MainStore {
    public static Map<String,List<String>> storeMap = new HashMap<>();

    static
    {
        List<String> ipList = new ArrayList();
        ipList.add("localhost:8080");
        ipList.add("localhost:8082");
        storeMap.put("order-service",ipList);
    }
}
