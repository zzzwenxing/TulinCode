package com.tuling.mvc.control;/**
 * Created by Administrator on 2018/8/31.
 */

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/31
 **/
public class SimpleControl implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("userView");
        mv.addObject("name", "luban is good man");
        return mv;
    }
}
