package com.jiagouedu.com.jdbc;/*
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　永无BUG 　┣┓
 * 　　　　┃　　如来保佑　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * 图灵学院-悟空老师
 * www.jiagouedu.com
 * 悟空老师QQ：245553999
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/***
 * 非对象
 */
public class Jdbc01 {

  public static void main(String[] args) {

    insert("wukong",12);
  }

  static void insert(String name,int age)
  {
    String sql="insert into user(username,age) value(?,?)";
    Connection conn=DbUtil.open();
    try {
      PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
      pstmt.setString(1,name);
      pstmt.setInt(2,age);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      DbUtil.close(conn);
    }

  }


}
