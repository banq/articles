package com.example.zuulrouter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class AuthHeaderFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";//关键在这里，四种类型定义
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getAttribute("AUTH_HEADER") == null) {
            //产生或检查AUTH_TOKEN,
            String sessionId = UUID.randomUUID().toString();

            ctx.addZuulRequestHeader("AUTH_HEADER", sessionId);
        }
        return null;
    }

}