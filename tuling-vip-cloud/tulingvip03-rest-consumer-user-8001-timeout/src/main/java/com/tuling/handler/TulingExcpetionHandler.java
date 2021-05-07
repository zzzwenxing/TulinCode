package com.tuling.handler;

import com.tuling.entity.UserInfoVo;
import com.tuling.exception.TulingTimeoutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by smlz on 2019/4/12.
 */
@ControllerAdvice
public class TulingExcpetionHandler {

    @ExceptionHandler(value = TulingTimeoutException.class)
    @ResponseBody
    public Object dealException() {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserName("容错用户");
        userInfoVo.setOrderVoList(null);

        return userInfoVo;
    }
}
