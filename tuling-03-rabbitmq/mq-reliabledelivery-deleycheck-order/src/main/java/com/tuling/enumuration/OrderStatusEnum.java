package com.tuling.enumuration;

import lombok.Getter;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:消息状态枚举
* @author: smlz
* @createDate: 2019/10/11 17:14
* @version: 1.0
*/
@Getter
public enum OrderStatusEnum {

    SUCCESS(0,"订单生成"),

    ERROR(1,"订单作废");



    private Integer code;

    private String msgStatus;



    OrderStatusEnum(Integer code, String msgStatus) {
        this.code = code;
        this.msgStatus = msgStatus;
    }


}
