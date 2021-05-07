package com.jiagouedu.session;/*
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

import com.jiagouedu.binding.MapperMethod;
import com.jiagouedu.binding.MapperProxy;
import com.jiagouedu.executor.Executor;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession {
  private Configuration configuration;
  private Executor executor;

  public Configuration getConfiguration() {
    return configuration;
  }

  public DefaultSqlSession(Configuration configuration, Executor executor) {
    this.configuration = configuration;
    this.executor = executor;
  }

  public <T> T getMapper(Class<T> type) {
    return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type},new MapperProxy<>(this,type));
  }
  @Override
  public <T> T selectOne(MapperMethod mapperMethod, Object statement) throws Exception {
    System.out.println();
    return  executor.query(mapperMethod,statement);
  }
}
