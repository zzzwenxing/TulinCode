package com.tuling.support.utils;

import java.util.UUID;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述
* @author: smlz
* @createDate: 2019/7/26 19:58
* @version: 1.0
*/
public class AngleDTUtils {

    /**
     * 方法实现说明：生成事务全局ID
     * @author:smlz
     * @return:String 事务全局ID
     * @date:2019/7/26 20:41
     */
    public static String generatorGlobalTransactionalId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 方法实现说明:生成子事务ID
     * @author:smlz
     * @return:String 子事务ID
     * @date:2019/7/26 20:42
     */
    public static String generatorChildTransactionalId() {
        return UUID.randomUUID().toString();
    }
}

