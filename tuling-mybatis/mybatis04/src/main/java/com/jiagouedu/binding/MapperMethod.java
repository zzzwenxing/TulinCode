package com.jiagouedu.binding;/*
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

/***
 * 把我们解析的sql（UserMapper）加载到这个类中
 * @param <T>
 */
public class MapperMethod<T> {
  private String sql;
  private Class<T> type;

  public MapperMethod(String sql, Class<T> type) {
    this.sql = sql;
    this.type = type;
  }
  public MapperMethod() {

  }
  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public Class<T> getType() {
    return type;
  }

  public void setType(Class<T> type) {
    this.type = type;
  }
}
