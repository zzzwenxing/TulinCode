package com.tuling.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述 servlet3.0 新特性 注册servlet
* @author: smlz
* @createDate: 2019/7/31 16:43
* @version: 1.0
*/
@WebServlet(value = {"/tulingHello"})
public class TulingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("hello tuling");
    }
}
