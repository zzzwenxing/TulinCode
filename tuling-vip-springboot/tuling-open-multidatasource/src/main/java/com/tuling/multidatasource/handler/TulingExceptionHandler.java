package com.tuling.multidatasource.handler;

import com.tuling.multidatasource.exception.TulingMultiDsError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 内部异常处理
 * Created by smlz on 2019/4/17.
 */
@ControllerAdvice
public class TulingExceptionHandler {

    @ResponseBody
    @ExceptionHandler({TulingMultiDsError.class})
    public Map<String,Object> dealException(Exception e){
        Map<String,Object> errorMap = new HashMap<>();

        if(e instanceof TulingMultiDsError) {
            TulingMultiDsError tulingMultiDsError = (TulingMultiDsError) e;
            errorMap.put("code", tulingMultiDsError.getErrorCode());
            errorMap.put("errMsg", tulingMultiDsError.getErrorMsg());
        }
        return errorMap;
    }
}
