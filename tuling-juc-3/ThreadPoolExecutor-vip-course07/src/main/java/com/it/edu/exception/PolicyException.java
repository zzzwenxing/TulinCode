package com.it.edu.exception;

/**
 * @author ：图灵-杨过
 * @date：2019/7/22
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description : 任务满拒绝异常
 */
public class PolicyException extends RuntimeException {

    public PolicyException() {
        super();
    }

    public PolicyException(String message) {
        super(message);
    }

}
