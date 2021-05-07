package com.tuling.support.holder;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Created by smlz on 2019/7/28.
 */
@ConfigurationProperties(prefix = "angle.dt")
@Data
public class AngleDTProperties {

    private long initialDelay = 1;

    private long delay = 1;

    private Integer watingTime = 5;
}
