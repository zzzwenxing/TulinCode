package com.tuling.version3;

import com.tuling.version3.config.RedisAutoConfig;
import com.tuling.version3.support.RedisCfgProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

/**
 * Created by smlz on 2019/8/31.
 */
@Slf4j
public class TestBaseJavaConfig {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(RedisAutoConfig.class);

		RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

		redisTemplate.opsForValue().set("smlz","司马");

		System.out.println("================"+redisTemplate.opsForValue().get("smlz"));
		/*MultiValueMap<String,String> provinceMappingCity = new LinkedMultiValueMap<String, String>();
		provinceMappingCity.add("湖南","长沙");
		provinceMappingCity.add("湖南","常德");
		provinceMappingCity.add("湖南","岳阳");

		provinceMappingCity.add("广东","广州");
		provinceMappingCity.add("广东","东莞");

		System.out.println(provinceMappingCity);
		System.out.println(provinceMappingCity.getOrDefault("广东", Collections.emptyList()));
*/


	}
}
