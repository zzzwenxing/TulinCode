package com.tuling.multidatasource.enumuration;

import lombok.Getter;

/**
 * 多数据源错误异常枚举类
 * Created by smlz on 2019/4/16.
 */
@Getter
public enum  MultiDsErrorEnum {

    ROUTINGFIELD_ARGS_ISNULL(0,"多数据源路由键为空"),

    LOADING_STATEGY_UN_MATCH(1,"路由组件加载和配置文件不匹配"),

    FORMAT_TABLE_SUFFIX_ERROR(2,"格式化表后缀异常"),

    PARAMS_NOT_CONTAINS_ROUTINGFIELD(3,"入参中不包含路由字段");

    private Integer code;

    private String msg;

    MultiDsErrorEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

}
