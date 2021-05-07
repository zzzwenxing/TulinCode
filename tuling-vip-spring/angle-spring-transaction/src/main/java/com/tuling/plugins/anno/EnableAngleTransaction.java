package com.tuling.plugins.anno;

import com.tuling.plugins.config.AngleTransactionConfiguration;
import com.tuling.plugins.core.postprocessor.TransactionBeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开始事务功能
 * Created by smlz on 2019/7/3.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TransactionBeanPostProcessor.class,AngleTransactionConfiguration.class})
public @interface EnableAngleTransaction {
}
