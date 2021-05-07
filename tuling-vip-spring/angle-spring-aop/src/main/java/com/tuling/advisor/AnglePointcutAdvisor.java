package com.tuling.advisor;


import com.tuling.pointcut.AnglePointCut;

/**
 * 切点通知
 * Created by smlz on 2019/6/28.
 */
public interface AnglePointcutAdvisor extends AngleAdvisor {

    /**
     * 获取切点
     * @return AnglePointCut
     */
    AnglePointCut getAnglePointCut();
}
