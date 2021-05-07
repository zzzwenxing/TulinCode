package com.tuling.support.holder;


/**
* @vlog: 高于生活，源于生活
* @desc: 线程局部变量持有器，用户同一个线程传递全局事务ID
* @author: smlz
* @createDate: 2019/7/26 19:55
* @version: 1.0
*/
public class AngleTransactionalHolder {

    public static final ThreadLocal<String> GLOBALTRANSACTIONID = new ThreadLocal<>();

    public static final ThreadLocal<String> CHILDTRANSACTIONID = new ThreadLocal<>();

    public static void set(String globalTransactionId) {
        GLOBALTRANSACTIONID.set(globalTransactionId);
    }

    public static String get() {
        return GLOBALTRANSACTIONID.get();
    }

    public static void setChild(String globalTransactionId) {
        CHILDTRANSACTIONID.set(globalTransactionId);
    }

    public static String getChild() {
        return CHILDTRANSACTIONID.get();
    }
}
