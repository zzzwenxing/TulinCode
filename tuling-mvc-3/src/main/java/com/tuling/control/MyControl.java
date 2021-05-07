package com.tuling.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tommy
 * Created by Tommy on 2019/7/10
 **/
@Controller
@RequestMapping("/my/")
public class MyControl {
    @RequestMapping("hello")
    public void hello(HttpServletResponse response,String name) throws IOException {
        response.getWriter().println("hello luban good man");
    }
}
