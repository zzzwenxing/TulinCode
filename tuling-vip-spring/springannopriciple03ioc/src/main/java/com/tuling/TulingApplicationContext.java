package com.tuling;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/29.
 */
public class TulingApplicationContext extends AnnotationConfigApplicationContext {

    protected void initPropertySources() {
        getEnvironment().setRequiredProperties("tuling");
    }

    public  TulingApplicationContext(Class<?>... annotatedClasses){
        super(annotatedClasses);
    }
}
