package com.tuling.compent;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by smlz on 2019/3/28.
 */
public class TulingImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"com.tuling.compent.TulingServiceImpl"};
    }
}
