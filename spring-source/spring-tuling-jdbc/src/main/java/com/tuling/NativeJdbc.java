package com.tuling;

import java.sql.*;

/**
 * 原生的JDBC
 * Created by smlz on 2019/8/18.
 */
public class NativeJdbc {

	public static final String url = "jdbc:mysql://localhost:3306/tuling-spring-trans";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		//sqlInject();
		defenseSqlInject();

	}

	private static void sqlInject() throws ClassNotFoundException, SQLException {
		//1:加载驱动
		Class.forName("com.mysql.jdbc.Driver");

		//2:创建连接
		Connection connection = DriverManager.getConnection(url,"root","Zw726515");

		//3:Statement 对象用于将 SQL 语句发送到数据库中
		Statement statement = connection.createStatement();

		//4:执行sql,返回结果集
		//ResultSet resultSet = statement.executeQuery("select * from account_info;");
		//模拟sql注入
		String sql = "select * from user_info where user_name="+"'zhangsan'"+" and password="+" 2423413 or 1=1";
		System.out.println("sql=="+sql);
		ResultSet resultSet = statement.executeQuery(sql);

		//5:循环结果集
		while (resultSet.next()) {
			//获取每一列数据
			Integer id = resultSet.getInt("id");
			String userName = resultSet.getString("user_name");
			String password = resultSet.getString("password");

			System.out.println("id:"+id+"-"+"userName:"+"-"+userName+"password:"+"-"+password);
		}
		resultSet.close();
		connection.close();
	}

	private static void defenseSqlInject() throws ClassNotFoundException, SQLException {
		//1:加载驱动
		Class.forName("com.mysql.jdbc.Driver");

		//2:创建连接
		Connection connection = DriverManager.getConnection(url,"root","Zw726515");

		String sql = "select * from user_info where user_name = ? and password= ? ";


		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setObject(1,"zhangsan");
		preparedStatement.setObject(2,"123 or 1=1");

		System.out.println("preparedStatement:"+preparedStatement.toString());

		ResultSet resultSet = preparedStatement.executeQuery();


		//5:循环结果集
		while (resultSet.next()) {
			//获取每一列数据
			Integer id = resultSet.getInt("id");
			String userName = resultSet.getString("user_name");
			String password = resultSet.getString("password");

			System.out.println("id:"+id+"-"+"userName:"+"-"+userName+"password:"+"-"+password);
		}
		resultSet.close();
		connection.close();

	}
}
