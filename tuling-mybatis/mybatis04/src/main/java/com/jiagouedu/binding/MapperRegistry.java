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





import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

  private Map<String, MapperMethod> knownMappers = new HashMap<String, MapperMethod>();

  public Map<String, MapperMethod> getKnownMappers() {
    return knownMappers;
  }

  public void setKnownMappers(Map<String, MapperMethod> knownMappers) {
    this.knownMappers = knownMappers;
  }
}
