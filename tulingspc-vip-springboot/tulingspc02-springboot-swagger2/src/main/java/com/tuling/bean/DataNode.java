package com.tuling.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/3/24.
 */
public class DataNode {

    public static Map<String,User> users = new HashMap<>();

    static {
        User zhangsan = new User(1,"zhangsan","123456","aa@qq.com","199007");
        User lisi = new User(2,"lisi","123456","bb@qq.com","199107");
        User wangwu = new User(3,"wangwu","123456","cc@qq.com","199207");
        User zhaoliu = new User(4,"zhaoliu","123456","dd@qq.com","199307");

        users.put("zhangsan",zhangsan);
        users.put("lisi",lisi);
        users.put("wangwu",wangwu);
        users.put("zhaoliu",zhaoliu);

    }
}
