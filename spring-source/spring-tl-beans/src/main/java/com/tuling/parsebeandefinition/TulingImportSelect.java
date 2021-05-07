package com.tuling.parsebeandefinition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现了ImportSelector接口的
 * Created by smlz on 2019/7/15.
 */
public class TulingImportSelect implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.tuling.parsebeandefinition.CompentA"};
	}
}
