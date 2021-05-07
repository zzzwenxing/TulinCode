package com.tuling.control;/**
 * Created by Administrator on 2018/9/17.
 */

import com.tuling.mvc.FreemarkeView;
import com.tuling.mvc.MvcMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tommy
 * Created by Tommy on 2018/9/17
 **/
@Service
public class HelloMVc {

    @MvcMapping("/hello.do")
    public FreemarkeView SayHello(String name, HttpServletRequest request, HttpServletResponse response) {
        FreemarkeView freemarkeView = new FreemarkeView("luban.ftl");
        freemarkeView.setModel("name", name);
        return freemarkeView;
    }

    @MvcMapping("/hi/*/hello2.do")
    public FreemarkeView SayHello2(String name, HttpServletRequest request, HttpServletResponse response) {
        FreemarkeView freemarkeView = new FreemarkeView("luban.ftl");
        freemarkeView.setModel("name", name);
        return freemarkeView;
    }
}
