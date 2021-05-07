package com.tuling.multidatasource.exception;

import com.tuling.multidatasource.enumuration.MultiDsErrorEnum;
import lombok.Data;

/**
 * 加载路由策略和配置配置文件不匹配
 * Created by smlz on 2019/4/17.
 */
@Data
public class LoadRoutingStategyUnMatch extends TulingMultiDsError {

    public LoadRoutingStategyUnMatch(MultiDsErrorEnum multiDsErrorEnum) {
        super();
        setErrorCode(multiDsErrorEnum.LOADING_STATEGY_UN_MATCH.getCode());
        setErrorMsg(multiDsErrorEnum.LOADING_STATEGY_UN_MATCH.getMsg());

    }
}
