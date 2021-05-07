package com.tuling.mvc.control;/**
 * Created by Administrator on 2018/8/31.
 */

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/31
 **/
public class BeanNameControl implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("luban is good man");
        int i = 1 / 0;
    }
}
