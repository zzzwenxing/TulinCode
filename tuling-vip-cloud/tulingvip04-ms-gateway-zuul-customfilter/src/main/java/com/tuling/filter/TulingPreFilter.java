package com.tuling.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义的zuul的过滤器
 * Created by smlz on 2019/4/23.
 */
//@Component
public class TulingPreFilter extends ZuulFilter {

    /**
     * filterType：返回过滤器的类型。有pre、route、post、error等几种取值
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 返回一个int值来指定过滤器的执行顺序，不同的过滤器允许返回相同的数字。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean值来判断该过滤器是否要执行，true表示执行，false表示不执行。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 真正的过滤顺序
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("通过自定义的路由器.......");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Cookie[] cookies = request.getCookies();
        if(cookies==null || cookies.length==0) {//不进行路由
            shouldNotFilter(ctx);
            return null;
        }else {

            ctx.setSendZuulResponse(true);
            //设置返回码
            ctx.setResponseStatusCode(200);

            ctx.set("isSuccess",true);

            return null;
        }

    }

    private void shouldNotFilter(RequestContext ctx) {
        //不进行路由
        ctx.setSendZuulResponse(false);
        //设置返回码
        ctx.setResponseStatusCode(401);
        //设置返回体
        ctx.setResponseBody("权限不够");
        ctx.set("isSuccess",false);
    }
}
