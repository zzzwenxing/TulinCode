package com.tuling.core;

import lombok.Data;

import java.io.Serializable;

/**
* @vlog: 高于生活，源于生活
* @desc: 分布式事务，子事务对象
* @author: smlz
* @createDate: 2019/7/26 20:54
* @version: 1.0
*/
@Data
public class ChildTransactionObj implements Serializable {

    /**
     * 子事务ID
     */
    private String childTransactionalId;

    /**
     * 全局事务ID
     */
    private String globalTransactionalId;

    /**
     * 子事务状态
     */
    private Integer TransationalEnumStatusCode;

    /**
     * 子事务类型
     */
    private Integer TransactionalTypeEunmCode;


}
