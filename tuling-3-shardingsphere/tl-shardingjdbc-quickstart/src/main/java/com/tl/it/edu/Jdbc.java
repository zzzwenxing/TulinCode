package com.tl.it.edu;

import java.sql.*;

/**
 *                  ,;,,;
 *                ,;;'(    社
 *      __      ,;;' ' \   会
 *   /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 *,;' \    /-.,,(   ) \    码
 *     ) /       ) / )|    农
 *     ||        ||  \)     
 *     (_\       (_\
 * @author ：杨过
 * @date ：Created in 2019/11/25 17:27
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 
 **/
public class Jdbc {

    /*
     * sharding-proxy,偏服务端分库分表代理Jdbc连接测试
     */
    public static void main(String[] args) {
        try {
             Class.forName("com.mysql.jdbc.Driver");
             String user = "tuling";
             String pwd = "tuling";
             String url = "jdbc:mysql://192.168.241.198:3308/shop_ds_proxy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
             Connection connection = DriverManager.getConnection(url,user,pwd);

             ResultSet statement = connection.createStatement().executeQuery("sctl:explain select * from t_order");

             while (statement.next()){
                 System.out.println("datasource-name:"+statement.getObject(1));
                 System.out.println("sql:"+statement.getObject(2));
             }

             connection.close();
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
}
