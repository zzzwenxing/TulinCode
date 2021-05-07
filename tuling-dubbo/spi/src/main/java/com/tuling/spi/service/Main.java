package com.tuling.spi.service;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Tommy
 * Created by Tommy on 2019/12/1
 **/
public class Main {
    public static void main(String[] args) {
        Iterator<UserService> iterator = ServiceLoader.load(UserService.class).iterator();
        UserService service = iterator.next();
        System.out.println(service.getName(11));
//        Class.forName("com.mysql.jdbc.Driver");
//        DriverManager.getConnection("");
    }
}
