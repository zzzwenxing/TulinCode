package com.tuling.version3.compent;

import com.tuling.version3.util.TulingAutoCfgReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Properties;

/**
 * Created by smlz on 2019/8/31.
 */
@Slf4j
public class TulingImportSelector implements ImportSelector {

	private Class<?> getSpringFactoriesLoaderFactoryClass() {
		return TulingEnableAutoConfig.class;
	}


	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		List<String> configurations = getCandidateConfigurations();
		System.out.println("configurations"+configurations);
		return StringUtils.toStringArray(configurations);
	}



	/**
	 * 超级屌丝版本
	 * @param importingClassMetadata
	 * @return

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.tuling.version3.config.SpringRedisConfig"};
	}
	 */







	/**
	 * 自定义的自动装配 原理
	 * @return

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {

		Properties properties = TulingAutoCfgReader.readerProperties("/tulingautoconfig.properties");

		String propValue = properties.getProperty(TulingEnableAutoConfig.class.getName());

		return new String[]{propValue};
	}
	 */






	protected List<String> getCandidateConfigurations() {
		List<String> configurations = SpringFactoriesLoader.loadFactoryNames(
				getSpringFactoriesLoaderFactoryClass(), TulingImportSelector.class.getClassLoader());
		Assert.notEmpty(configurations,
				"No auto configuration classes found in META-INF/spring.factories. If you "
						+ "are using a custom packaging, make sure that file is correct.");
		return configurations;
	}
}
