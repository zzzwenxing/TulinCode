package com.tuling.spring.tx;/**
 * Created by Administrator on 2018/8/28.
 */

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * spring 编程示事物示例
 * @author Tommy
 *         Created by Tommy on 2018/8/28
 **/
public class SpringTransactionExample {
    private static String url = "jdbc:mysql://192.168.0.147:3306/luban2";
    private static String user = "root";
    private static String password = "123456";

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.147:3306/luban2", "root", "123456");
        return conn;
    }

    public static void main(String[] args) {
        final DataSource ds = new DriverManagerDataSource(url, user, password);

        final TransactionTemplate template = new TransactionTemplate();

        template.setTransactionManager(new DataSourceTransactionManager(ds));

        template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Connection conn = DataSourceUtils.getConnection(ds);
                Object savePoint = null;
                try {
                    {
                        // 插入
                        PreparedStatement prepare = conn.
                                prepareStatement("insert INTO account (accountName,user,money) VALUES (?,?,?)");
                        prepare.setString(1, "111");
                        prepare.setString(2, "aaaa");
                        prepare.setInt(3, 10000);
                        prepare.executeUpdate();
                    }
                    // 设置保存点
                   savePoint = status.createSavepoint();
                    {
                        // 插入
                        PreparedStatement prepare = conn.
                                prepareStatement("insert INTO account (accountName,user,money) VALUES (?,?,?)");
                        prepare.setString(1, "222");
                        prepare.setString(2, "bbb");
                        prepare.setInt(3, 10000);
                        prepare.executeUpdate();
                    }
                    {
                        // 更新
                        PreparedStatement prepare = conn.
                                prepareStatement("UPDATE account SET money= money+1 where user=?");
                        prepare.setString(1, "asdflkjaf");

                        int i=1/0;

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("更新失败");
                    if (savePoint != null) {
                        status.rollbackToSavepoint(savePoint);
                    } else {
                        status.setRollbackOnly();
                    }
                }
                return null;
            }
        });
    }


}
