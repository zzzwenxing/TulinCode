package com.tuling.exceptionhander;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/4/25.
 */
//@ControllerAdvice
public class TulingExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    public Map<String,Object> dealException(Exception e) {
        Map<String,Object> retMap = new HashMap<>();
        if(e instanceof RuntimeException) {
            retMap.put("code","-1");
            retMap.put("errMsg",e.getMessage());
        }
        return retMap;
    }
}
