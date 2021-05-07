package com.tuling.spring.tx;
/**
 * Created by Administrator on 2018/8/27.
 */

import java.sql.*;

/**
 * Connection.TRANSACTION_REPEATABLE_READ
 * 可重复读 ,在一个事物中同一SQL语句 无论执行多少次都会得到相同的结果
 *
 * @author Tommy
 *         Created by Tommy on 2018/8/27
 **/
public class ReadRepeatableExample {
    static {
        try {
            openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Object lock = new Object();

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.147:3306/luban2", "root", "123456");
        return conn;
    }

    public static void update(String user) {
        try {
            Connection conn = openConnection();
            PreparedStatement prepare = conn.
                    prepareStatement("UPDATE account SET money= money+1 where user=?");
            prepare.setString(1, user);
            prepare.executeUpdate();
            conn.close();
            System.out.println("执行修改成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
        // 执行修改
        Thread t1 = run(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                update("luban");
            }
        });

        Thread t2 = run(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conn = openConnection();
                    conn.setAutoCommit(false);
                    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    // 第一次读取
                    select("luban", conn);
                    // 释放锁
                    synchronized (lock) {
                        lock.notify();
                    }
                    // 第二次读取 ,采用了  REPEATABLE 级别，所以两次读取数据一至
                    Thread.sleep(500);
                    select("luban", conn);
                    conn.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
