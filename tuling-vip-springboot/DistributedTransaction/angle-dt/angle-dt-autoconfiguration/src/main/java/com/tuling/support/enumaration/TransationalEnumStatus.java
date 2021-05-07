package com.tuling.support.enumaration;

/**
* @vlog: 高于生活，源于生活
* @desc: 分布式事务中，事务状态枚举描述
* @author: smlz
* @createDate: 2019/7/26 19:50
* @Version: 1.0
*/
public enum  TransationalEnumStatus {

    COMMIT(1,"COMMIT"),

    WATING(0,"WATING"),

    ROLLBACK(-1,"ROOLBACK")
    ;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;

    private String msg;

    TransationalEnumStatus(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public static TransationalEnumStatus getbycode(Integer code) {
        for (TransationalEnumStatus transationalEnumStatus:values()) {
            if(transationalEnumStatus.getCode() == code) {
                return transationalEnumStatus;
            }
        }
        return null;
    }

}
