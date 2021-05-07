package com.tuling.core;



/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:AngleTrancationBulider构建子事务对象
* @author: smlz
* @createDate: 2019/7/26 21:59
* @version: 1.0
*/
public class AngleTrancationBulider {

    private ChildTransactionObj childTransactionObj;

    public AngleTrancationBulider() {
        childTransactionObj = new ChildTransactionObj();
    }

    public AngleTrancationBulider builderChildTransactionalId(String childTransactionalId){
        childTransactionObj.setChildTransactionalId(childTransactionalId);
        return this;
    }

    public AngleTrancationBulider builderTransationalEnumStatus(Integer transationalEnumStatusCode) {
        childTransactionObj.setTransationalEnumStatusCode(transationalEnumStatusCode);
        return this;
    }

    public AngleTrancationBulider buliderTransactionalTypeEunmCode(Integer transactionalTypeEunmCode) {
        childTransactionObj.setTransactionalTypeEunmCode(transactionalTypeEunmCode);
        return this;
    }

    public AngleTrancationBulider buliderGlobalTransactionId(String globalTransactionId) {
        childTransactionObj.setGlobalTransactionalId(globalTransactionId);
        return this;
    }

    public ChildTransactionObj builder() {
        return childTransactionObj;
    }
}
