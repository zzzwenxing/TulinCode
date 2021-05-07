package com.tuling.multidatasource.exception;

import com.tuling.multidatasource.enumuration.MultiDsErrorEnum;

/**
 * 入参中没有包含路由字段异常
 * Created by smlz on 2019/4/18.
 */

public class ParamsNotContainsRoutingField extends TulingMultiDsError {


    public ParamsNotContainsRoutingField(MultiDsErrorEnum paramsNotContainsRouting) {
        super();
        setErrorCode(paramsNotContainsRouting.PARAMS_NOT_CONTAINS_ROUTINGFIELD.getCode());
        setErrorMsg(paramsNotContainsRouting.PARAMS_NOT_CONTAINS_ROUTINGFIELD.getMsg());

    }
}
