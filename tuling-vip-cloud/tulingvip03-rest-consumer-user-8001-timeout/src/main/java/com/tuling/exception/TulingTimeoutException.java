package com.tuling.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by smlz on 2019/4/12.
 */

@Data
@AllArgsConstructor
public class TulingTimeoutException extends RuntimeException {

    private Integer code;

    private String msg;
}
