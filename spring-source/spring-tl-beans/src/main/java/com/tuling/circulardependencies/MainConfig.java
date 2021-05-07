package com.tuling.circulardependencies;

import org.springframework.context.annotation.*;

/**
 * Created by smlz on 2019/5/29.
 */
@Configuration
@ComponentScan(basePackages = {"com.tuling.circulardependencies"})
@ImportResource(value = {"classpath:beans/beans.xml"})
public class MainConfig {



}
