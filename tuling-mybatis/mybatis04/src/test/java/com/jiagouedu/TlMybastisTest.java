package com.jiagouedu;/*
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

import com.jiagouedu.mapper.UserMapper;
import com.jiagouedu.pojo.User;
import com.jiagouedu.session.Configuration;
import com.jiagouedu.session.DefaultSqlSession;
import com.jiagouedu.session.SqlSessionFactory;
import com.jiagouedu.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class TlMybastisTest {
  public static void main(String[] args) throws IOException {
    InputStream inputStream = TlMybastisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
    Configuration configuration = new Configuration();
    configuration.setInputStream(inputStream);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    DefaultSqlSession sqlSession = (DefaultSqlSession) sqlSessionFactory.openSession(configuration);
    UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
    User user = userMapper.getUser(1);
    System.out.println(user);
  }
}
