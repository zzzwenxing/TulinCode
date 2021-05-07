package com.tuling.multidatasource.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by smlz on 2019/4/17.
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class TulingDruidProperties {

    private String druid00username;

    private String druid00passwrod;

    private String druid00jdbcUrl;

    private String druid00driverClass;

    private String druid01username;

    private String druid01passwrod;

    private String druid01jdbcUrl;

    private String druid01driverClass;

    private String druid02username;

    private String druid02passwrod;

    private String druid02jdbcUrl;

    private String druid02driverClass;
}
