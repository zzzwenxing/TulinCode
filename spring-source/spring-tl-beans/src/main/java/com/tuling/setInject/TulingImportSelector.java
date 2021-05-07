package com.tuling.setInject;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by smlz on 2019/8/26.
 */
public class TulingImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.tuling.setInject.InstE"};
	}
}
