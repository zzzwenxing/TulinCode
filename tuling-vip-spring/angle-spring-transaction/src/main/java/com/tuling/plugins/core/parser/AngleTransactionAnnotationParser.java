package com.tuling.plugins.core.parser;


import com.tuling.plugins.core.attribute.AngleTransactionAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 * 事务注解自定义解析器
 * Created by smlz on 2019/7/2.
 */
public interface AngleTransactionAnnotationParser {

    AngleTransactionAttribute parseTransactionAnnotation(AnnotatedElement element);
}
