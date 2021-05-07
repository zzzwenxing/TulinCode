package com.tuling.mvc.control;/**
 * Created by Administrator on 2018/8/31.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/31
 **/
@Controller
public class RequestMappingControl {

    @RequestMapping("/hello.do")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("userView");
        mv.addObject("name", "luban");
        return mv;
    }
}
