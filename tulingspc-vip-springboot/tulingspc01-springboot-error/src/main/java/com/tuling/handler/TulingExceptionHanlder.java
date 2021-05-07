package com.tuling.handler;

import com.tuling.exception.TulingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/3/19.
 */
@ControllerAdvice
public class TulingExceptionHanlder {


    /**
     * 浏览器和其他客户端都返回了json 数组，不满足自适应
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value= TulingException.class)
    @ResponseBody
    public Map<String,Object> dealException(TulingException e, HttpServletRequest request){
        Map<String,Object> retInfo = new HashMap<>();
        retInfo.put("code",e.getCode());
        retInfo.put("msg",e.getMsg());
        return retInfo;
    }

/*    @ExceptionHandler(value= TulingException.class)
    public String dealException(TulingException e, HttpServletRequest request){
        Map<String,Object> retInfo = new HashMap<>();
        retInfo.put("code",e.getCode());
        retInfo.put("msg",e.getMsg());
        return "forward:/error";
    }*/

/*    @ExceptionHandler(value= TulingException.class)
    public String dealException(TulingException e, HttpServletRequest request){
        Map<String,Object> retInfo = new HashMap<>();
        retInfo.put("code",e.getCode());
        retInfo.put("msg",e.getMsg());
        request.setAttribute("javax.servlet.error.status_code",500);
        request.setAttribute("ext",retInfo);
        return "forward:/error";
    }*/

}
