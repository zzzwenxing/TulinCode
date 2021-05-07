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

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
@Slf4j
public class MybatisTest {

  //一级缓存
  @Test
  public void test() throws IOException {

    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    log.info("user1：{}", sqlSession.selectOne("com.jiagouedu.mybatis.UserMapper.selectUser", 3));
    log.info("user2：{}", sqlSession.selectOne("com.jiagouedu.mybatis.UserMapper.selectUser", 3));
    sqlSession.commit();
    log.info("user3：{}", sqlSession.selectOne("com.jiagouedu.mybatis.UserMapper.selectUser", 3));
    log.info("user4：{}", sqlSession.selectOne("com.jiagouedu.mybatis.UserMapper.selectUser", 3));
  }
}
