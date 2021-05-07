package com.tuling.testparentsonbean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by smlz on 2019/6/3.
 */
@Configuration
@ImportResource(locations = "classpath:Beans.xml")
public class MainConfig {
}
