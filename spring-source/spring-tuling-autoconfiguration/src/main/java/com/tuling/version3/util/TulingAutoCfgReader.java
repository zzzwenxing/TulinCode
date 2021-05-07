package com.tuling.version3.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 自动装配读取器
 * Created by smlz on 2019/9/17.
 */
public class TulingAutoCfgReader {

	public static Properties readerProperties(String resource) {
		Properties properties = new Properties();
		InputStream inputStream = TulingAutoCfgReader.class.getResourceAsStream(resource);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			System.out.println("读取配置文件异常"+e.getMessage());
		}
		return properties;
	}

}
