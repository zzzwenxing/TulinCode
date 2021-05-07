package com.tuling.exception;

/**
 * Created by smlz on 2019/3/19.
 */
public class TulingException extends RuntimeException {

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TulingException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
