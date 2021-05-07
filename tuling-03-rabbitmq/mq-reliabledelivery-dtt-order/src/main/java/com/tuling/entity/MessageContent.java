package com.tuling.entity;

import lombok.Data;

import java.util.Date;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:消息日志表
* @author: smlz
* @createDate: 2019/10/11 16:10
* @version: 1.0
*/
@Data
public class MessageContent {

    private String msgId;

    private long orderNo;

    private Date createTime;

    private Date updateTime;

    private Integer msgStatus;

    private String exchange;

    private String routingKey;

    private String errCause;

    private Integer maxRetry;

    private Integer currentRetry=0;

    private Integer productNo;
}
