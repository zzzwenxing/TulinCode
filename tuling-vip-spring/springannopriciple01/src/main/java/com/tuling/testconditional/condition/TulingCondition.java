package com.tuling.testconditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * Created by smlz on 2019/5/20.
 */
public class TulingCondition implements Condition {

    /**
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //判断容器中是否有tulingAspect的组件
        if(context.getBeanFactory().containsBean("tulingAspect")) {
            return true;
        }
        return false;
    }
}
