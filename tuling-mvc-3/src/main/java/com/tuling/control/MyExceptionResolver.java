package com.tuling.control;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Tommy
 * Created by Tommy on 2019/7/10
 **/
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        if (ex instanceof IllegalArgumentException) {
            modelAndView.addObject("errorType", "参数非法");
        } else {
            modelAndView.addObject("errorType", ex.getClass().getSimpleName());
        }
        modelAndView.addObject("message", ex.getMessage());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(out, true));
        modelAndView.addObject("stack", out.toString());
        return modelAndView;
    }
}
