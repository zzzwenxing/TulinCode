package com.tuling.busi;

import com.alibaba.druid.pool.DruidDataSource;
import com.tuling.plugins.anno.EnableAngleTransaction;
import com.tuling.plugins.core.advisor.AngleTransactionAttributeSourceAdvisor;
import com.tuling.plugins.core.interceptors.AngleTransactionInterceptor;
import com.tuling.plugins.core.source.AngleTransactionAttributeSource;
import com.tuling.plugins.core.source.AnnotationAngleTransactionAttributeSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by smlz on 2019/7/3.
 */
@EnableAngleTransaction
@ComponentScan(basePackages = {"com.tuling.busi"})
public class MainConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("Zw726515");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tuling-spring-trans");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
