package com.tuling.control;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tommy
 * Created by Tommy on 2019/7/9
 **/
@ResponseStatus(code = HttpStatus.GATEWAY_TIMEOUT, reason = "防问超时")
public class ResponseStatusException extends RuntimeException {
    public ResponseStatusException() {
    }

    public ResponseStatusException(String message) {
        super(message);
    }
}
