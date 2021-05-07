package com.tuling.testlookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.annotation.Autowire.BY_NAME;

/**
 * Created by smlz on 2019/6/6.
 */
@Configuration
@ComponentScan(basePackages = {"com.tuling.testlookup"})
public class MainConfig {


}
