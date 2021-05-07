package com.tuling.spring;/**
 * Created by Administrator on 2018/8/26.
 */

import org.springframework.beans.factory.FactoryBean;

import java.sql.DriverManager;

/**
 *
 * 自定义的创建 我们的Bean
 * @author Tommy
 *         Created by Tommy on 2018/8/26
 **/
public class DriverFactoryBean implements FactoryBean {
    private String jdbcUrl;

    @Override
    public Object getObject() throws Exception {
        return DriverManager.getDriver(jdbcUrl);
    }

    @Override
    public Class<?> getObjectType() {
        return java.sql.Driver.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
