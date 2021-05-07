package com.tuling.testapplicationlistener;

import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;


/**
 * Created by smlz on 2019/6/13.
 */
@Component(value = "applicationEventMulticaster")
public class TulingMulticaster extends SimpleApplicationEventMulticaster{

    public TulingMulticaster () {
        setTaskExecutor(Executors.newSingleThreadExecutor());
    }

}
