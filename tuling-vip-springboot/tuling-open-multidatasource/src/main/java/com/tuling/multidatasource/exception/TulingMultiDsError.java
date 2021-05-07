package com.tuling.multidatasource.exception;

import lombok.Data;

/**
 * 所有异常的父类
 * Created by smlz on 2019/4/18.
 */
@Data
public class TulingMultiDsError extends RuntimeException {

    private Integer errorCode;

    private String errorMsg;
}
