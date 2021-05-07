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
import com.jiagouedu.binding.MapperRegistry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 读取xml加载到内存中
 */
public class Configuration {
  private InputStream inputStream;

  MapperRegistry mapperRegistry=new MapperRegistry();



  /**通过Ｄｏｍ４ｊ读取配置文件信息*/
  public void loadConfigurations() throws IOException {
    try {
      Document document = new SAXReader().read(inputStream);
      Element root = document.getRootElement();
      List<Element> mappers = root.element("mappers").elements("mapper");
      for (Element mapper : mappers) {
        if (mapper.attribute("resource") != null) {
          mapperRegistry.setKnownMappers(loadXMLConfiguration(mapper.attribute("resource").getText()));
        }
        if (mapper.attribute("class") != null) {
        }
      }
    } catch (Exception e) {
      System.out.println("读取配置文件错误!");
    } finally {
      inputStream.close();
    }
  }

  /*** 通过ｄｏｍ４ｊ读取Ｍａｐｐｅｒ．ｘｍｌ中的信息*/
  private Map<String, MapperMethod> loadXMLConfiguration(String resource) throws DocumentException, IOException {
    Map<String, MapperMethod> map = new HashMap<String, MapperMethod>();
    InputStream is = null;
    try {
      is = this.getClass().getClassLoader().getResourceAsStream(resource);

      Document document = new SAXReader().read(is);
      Element root = document.getRootElement();
      if (root.getName().equalsIgnoreCase("mapper")) {
        String namespace = root.attribute("namespace").getText();
        for (Element select : (List<Element>) root.elements("select")) {
          MapperMethod mapperModel = new MapperMethod();
          mapperModel.setSql(select.getText().trim());
          mapperModel.setType(Class.forName(select.attribute("resultType").getText()));
          map.put(namespace + "." + select.attribute("id").getText(), mapperModel);
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      is.close();
    }
    return map;
  }


  public InputStream getInputStream() {
    return inputStream;
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public MapperRegistry getMapperRegistry() {
    return mapperRegistry;
  }

  public void setMapperRegistry(MapperRegistry mapperRegistry) {
    this.mapperRegistry = mapperRegistry;
  }
}
