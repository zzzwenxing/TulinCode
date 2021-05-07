package com.tuling.control;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tommy
 * Created by Tommy on 2019/7/7
 **/
public class SimpleControl implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("userView");
        modelAndView.addObject("name", "luban ");
        if (request.getParameter("name").length() < 2) {
            throw new IllegalArgumentException("名称不能少于2个字符");
        }
        int i = 1 / 0;
        return modelAndView;
    }
}
