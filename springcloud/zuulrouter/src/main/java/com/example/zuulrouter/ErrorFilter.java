package com.example.zuulrouter;

import com.netflix.zuul.ZuulFilter;

public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
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
        System.out.println("Error：Using Route Filter");

        return null;
    }

}
