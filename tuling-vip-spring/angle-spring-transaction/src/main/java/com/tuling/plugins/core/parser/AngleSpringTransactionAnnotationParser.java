package com.tuling.plugins.core.parser;

import com.tuling.plugins.anno.AngleTransactionl;
import com.tuling.plugins.core.attribute.AngleTransactionAttribute;
import com.tuling.plugins.core.attribute.DefaultAngleTransactionAttribute;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的注解解析器接口
 * Created by smlz on 2019/7/2.
 */
public class AngleSpringTransactionAnnotationParser implements AngleTransactionAnnotationParser {

    public AngleTransactionAttribute parseTransactionAnnotation(AnnotatedElement element) {

        AngleTransactionl angleTransactionl = element.getAnnotation(AngleTransactionl.class);
        if (angleTransactionl != null) {
            return parseTransactionAnnotation(angleTransactionl);
        }
        else {
            return null;
        }
    }

    /**
     * 解析事务@AngleTransactionl注解的属性 封装为DefaultAngleTransactionAttribute返回
     * @param angleTransactionl 事务注解的属性
     * @return 封装为DefaultAngleTransactionAttribute返回
     */
    protected AngleTransactionAttribute parseTransactionAnnotation(AngleTransactionl angleTransactionl) {
        DefaultAngleTransactionAttribute defaultAngleTransactionAttribute = new DefaultAngleTransactionAttribute();
        //设置传播行为
        Propagation propagation = angleTransactionl.propagation();
        defaultAngleTransactionAttribute.setPropagationBehavior(propagation.value());
        //设置隔离级别
        Isolation isolation = angleTransactionl.isolation();
        defaultAngleTransactionAttribute.setIsolationLevel(isolation.value());
        //设置超时
        defaultAngleTransactionAttribute.setTimeout(angleTransactionl.timeout());
        //设置只读事务
        defaultAngleTransactionAttribute.setReadOnly(angleTransactionl.readOnly());
        //设置事务管理器
        defaultAngleTransactionAttribute.setTransactionManager(angleTransactionl.transactionManager());

        RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();


        List<Class<?>> rollBackFor = new ArrayList<Class<?>>();
        for(Class<?> rollBackForEx : angleTransactionl.rollbackFor()){
            rollBackFor.add(rollBackForEx);
        }
        //设置对哪些异常回滚
        defaultAngleTransactionAttribute.setRollbackFor(rollBackFor);

        List<Class<?>> noRollbackFor = new ArrayList<Class<?>>();
        for(Class<?> norollBackForEx : angleTransactionl.noRollbackFor()){
            noRollbackFor.add(norollBackForEx);
        }
        //设置对哪些异常不回滚
        defaultAngleTransactionAttribute.setNoRollBackFor(noRollbackFor);
        return defaultAngleTransactionAttribute;
    }

}
