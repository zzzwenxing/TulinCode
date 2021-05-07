package com.tuling.spring.tx;
/**
 * Created by Administrator on 2018/8/27.
 */

import java.sql.*;

/**
 * Connection.TRANSACTION_READ_UNCOMMITTED
 * 允许读取未提交事物
 *
 * @author Tommy
 *         Created by Tommy on 2018/8/27
 **/
public class ReadUncommittedExample {

    static {
        try {
            openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.147:3306/luban2", "root", "123456");
        return conn;
    }

    public static void insert(String accountName, String name, int money) {
        try {
            Connection conn = openConnection();
            conn.setAutoCommit(false);
            PreparedStatement prepare = conn.
                    prepareStatement("insert INTO account (accountName,user,money) VALUES (?,?,?)");
            prepare.setString(1, accountName);
            prepare.setString(2, name);
            prepare.setInt(3, money);
            prepare.executeUpdate();
            System.out.println("执行插入");
            Thread.sleep(3000);
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void select(String name, Connection conn) {
        try {

            PreparedStatement prepare = conn.
                    prepareStatement("SELECT * from account where user=?");
            prepare.setString(1, name);
            ResultSet resultSet = prepare.executeQuery();
            System.out.println("执行查询");
            while (resultSet.next()) {
                for (int i = 1; i <= 4; i++) {
                    System.out.print(resultSet.getString(i) + ",");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Thread run(Runnable runnable) {
        Thread thread1 = new Thread(runnable);
        thread1.start();
        return thread1;
    }

    public static void main(String[] args) {
        // 启动插入线程
        Thread t1 = run(new Runnable() {
            @Override
            public void run() {
                insert("1111", "luban", 10000);
            }
        });
        // 启动查询线程
        Thread t2 = run(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Connection conn = openConnection();
                    // 将参数升级成 Connection.TRANSACTION_READ_COMMITTED 即可解决脏读的问题
                    conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    select("luban", conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
