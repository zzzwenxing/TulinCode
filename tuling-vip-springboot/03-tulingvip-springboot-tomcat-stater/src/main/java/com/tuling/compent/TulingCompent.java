package com.tuling.compent;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by smlz on 2019/4/3.
 */

public class TulingCompent implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是InitializingBean的afterPropertiesSet方法");
    }

    public TulingCompent() {
        System.out.println("我是构造方法");
    }

    public void init() {
        System.out.println("我是TulingCompent的init方法");
    }
}
