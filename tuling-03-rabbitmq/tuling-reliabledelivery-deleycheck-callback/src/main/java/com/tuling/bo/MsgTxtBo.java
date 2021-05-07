package com.tuling.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:消息文本对象
* @author: smlz
* @createDate: 2019/10/11 17:30
* @version: 1.0
*/
@Getter
@Setter
public class MsgTxtBo implements Serializable {

    private long orderNo;

    private int productNo;

    private String msgId;
}
