package com.tuling.testprofiles;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * Created by smlz on 2019/5/24.
 */
@Configuration
@PropertySource(value = {"classpath:ds.properties"})
public class MainConfig implements EmbeddedValueResolverAware {

    @Value("${ds.username}")
    private String userName;

    @Value("${ds.password}")
    private String password;

    private String jdbcUrl;

    private String classDriver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.jdbcUrl = resolver.resolveStringValue("${ds.jdbcUrl}");
        this.classDriver = resolver.resolveStringValue("${ds.classDriver}");
    }

    @Bean
    @Profile(value = "test")
    public DataSource testDs() {
        return buliderDataSource(new DruidDataSource());
    }

    @Bean
    @Profile(value = "dev")
    public DataSource devDs() {
        return buliderDataSource(new DruidDataSource());
    }

    @Bean
    @Profile(value = "prod")
    public DataSource prodDs() {
        return buliderDataSource(new DruidDataSource());
    }

    private DataSource buliderDataSource(DruidDataSource dataSource) {
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(classDriver);
        dataSource.setUrl(jdbcUrl);
        return dataSource;
    }
}
