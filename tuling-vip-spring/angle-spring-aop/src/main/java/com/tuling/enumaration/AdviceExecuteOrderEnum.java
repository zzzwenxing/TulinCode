package com.tuling.enumaration;

/**
 * Created by smlz on 2019/7/1.
 * 切面执行顺序order取值  值越大 越先被执行
 */
public enum AdviceExecuteOrderEnum {

    BEFORE_ADVICE(3,"前置通知"),

    AFTER_ADVICE(2,"后置通知"),

    RETURING_ADVICE(1,"返回通知"),

    THROWING_ADVICE(0,"异常通知")
    ;

    public Integer getExeOrder() {
        return exeOrder;
    }

    private Integer exeOrder;

    private String exeAdvice;

    AdviceExecuteOrderEnum(Integer exeOrder, String exeAdvice) {
        this.exeOrder = exeOrder;
        this.exeAdvice = exeAdvice;
    }
}
