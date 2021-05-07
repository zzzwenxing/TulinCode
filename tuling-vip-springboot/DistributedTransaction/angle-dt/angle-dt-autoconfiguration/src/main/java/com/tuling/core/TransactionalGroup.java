package com.tuling.core;

import lombok.Data;

import java.util.List;

/**
* @vlog: 高于生活，源于生活
* @desc: 分布式事务组对象
* @author: smlz
* @createDate: 2019/7/26 20:56
* @version: 1.0
*/
@Data
public class TransactionalGroup {

    /**
     * 全局事务ID
     */
    private String globalTransactionalId;

    /**
     * 该事务组下所有子事务集合
     */
    private List<ChildTransactionObj> childTransactionObjs;
}
