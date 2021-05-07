package com.jiagouedu.executor;/*
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
import com.jiagouedu.executor.Executor;
import com.jiagouedu.session.Configuration;
import com.jiagouedu.statement.StatementHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class SimpleExecutor implements Executor {
  private Configuration configuration;

  public SimpleExecutor(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public <T> T query(MapperMethod method, Object parameter) throws Exception
       {
    StatementHandler statementHandler = new StatementHandler(configuration);
    return statementHandler.query(method,parameter);
  }
}
