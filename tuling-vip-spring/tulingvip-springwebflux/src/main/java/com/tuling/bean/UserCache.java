package com.tuling.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/6/26.
 */
public class UserCache {

    public static final Map<Integer,User> userInfos = new HashMap<>();

    static {
        userInfos.put(1,new User(1,"张三",18));
        userInfos.put(2,new User(2,"李四",19));
        userInfos.put(3,new User(3,"王五",20));
    }
}
