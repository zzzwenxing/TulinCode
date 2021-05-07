package com.tuling.support.enumaration;


/**
* @vlog: 高于生活，源于生活
* @desc: 分布式事务中 子事务状态描述枚举
* @author: smlz
* @createDate: 2019/7/26 19:49
* @version: 1.0
*/
public enum TransactionalTypeEunm {
    
    BEGIN(1,"事务开始,需要创建事务组对象"),
    
    ADD(0,"添加到事务组中"),
    
    END(-1,"事务结束,需要计算事务组中各个子事务结果");

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {

        return code;
    }

    private Integer code;

    private String msg;

    TransactionalTypeEunm(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
