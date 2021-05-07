package com.macro.mall.controller.advice;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ：杨过
 * @date ：Created in 2020/3/22
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(Exception ex){
        if(ex instanceof BusinessException){
           return CommonResult.failed("请稍后再试:"+ex.getMessage());
        }else if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException e = (MethodArgumentNotValidException)ex;
            return CommonResult.failed("校验错误:"+e.getBindingResult().getFieldError().getDefaultMessage());
        }
        return CommonResult.failed();
    }

}
