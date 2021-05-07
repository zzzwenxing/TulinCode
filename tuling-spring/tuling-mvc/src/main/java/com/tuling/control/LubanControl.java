package com.tuling.control;/**
 * Created by Administrator on 2018/9/1.
 */

import com.tuling.mvc.FreemarkeView;
import com.tuling.mvc.MvcMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tommy
 *         Created by Tommy on 2018/9/1
 **/
@Controller
public class LubanControl {

    @MvcMapping("/luban.do")
    public FreemarkeView openLubanPage(String name) {
        FreemarkeView freemarkeView = new FreemarkeView("luban.ftl");
        freemarkeView.setModel("name", name);
        return freemarkeView;
    }

    @MvcMapping("/hello.do")
    public FreemarkeView open(String name, BlogDoc doc,
                              HttpServletRequest request, HttpServletResponse resp) {
        FreemarkeView freemarkeView = new FreemarkeView("lluban.ft");
        freemarkeView.setModel("name", name);
        return freemarkeView;
    }
}
