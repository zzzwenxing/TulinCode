package com.tuling.plugins.core.source;

import com.tuling.plugins.core.parser.AngleSpringTransactionAnnotationParser;
import com.tuling.plugins.core.parser.AngleTransactionAnnotationParser;
import com.tuling.plugins.core.attribute.AngleTransactionAttribute;
import lombok.Data;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 注解的angle事务属性源
 * Created by smlz on 2019/7/2.
 */
@Data
public class AnnotationAngleTransactionAttributeSource extends AbstractAngleTransactionAttributeSource {

    private  Set<AngleTransactionAnnotationParser> annotationParsers =new HashSet<AngleTransactionAnnotationParser>();

    public AnnotationAngleTransactionAttributeSource() {
        //为注解的AngleTransactionAttributeSource设置解析器
        this.annotationParsers.add(new AngleSpringTransactionAnnotationParser());
    }


    //获取注解属性
    public AngleTransactionAttribute determineTransactionAttribute(AnnotatedElement element) {
        //如果获取到注解属性对象
        if (element.getAnnotations().length > 0) {
            //通过注解解析器来解析事务注解
            for (AngleTransactionAnnotationParser annotationParser : this.annotationParsers) {
                AngleTransactionAttribute attr = annotationParser.parseTransactionAnnotation(element);
                if (attr != null) {
                    return attr;
                }
            }
        }
        return null;
    }

    protected AngleTransactionAttribute findTransactionAttribute(Method method) {
        return determineTransactionAttribute(method);
    }
}
