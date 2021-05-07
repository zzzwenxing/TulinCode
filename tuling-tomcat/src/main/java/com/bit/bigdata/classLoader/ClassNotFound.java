package com.bit.bigdata.classLoader;

import junit.framework.TestCase;

/**
 * @author Tommy
 * Created by Tommy on 2019/9/4
 **/
public class ClassNotFound {
    // 编译不报错，执行却不通过
    public static void main(String[] args) {
        TestCase.class.toString();
    }
}
