package com.tuling.advisor;

import com.tuling.advice.AngleAdvice;

/**
 * 切面中的的通知者接口(用来描述我们的 @AngleBefore,,,,@AngleAfter等等标注的接口)
 * Created by smlz on 2019/6/28.
 */
public interface AngleAdvisor {

    /**
     * 获取所在通知对象
     * @return
     */
    AngleAdvice getAdvice();

/*    *//**
     * 获取表达式
     * @return
     *//*
    String getExpression();*/
}
