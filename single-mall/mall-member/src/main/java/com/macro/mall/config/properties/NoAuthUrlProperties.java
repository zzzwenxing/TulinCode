package com.macro.mall.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

/**
 * @author ：杨过
 * @date ：Created in 2020/2/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Data
@ConfigurationProperties(prefix = "member.auth")
public class NoAuthUrlProperties {
    private LinkedHashSet<String> shouldSkipUrls;
}
