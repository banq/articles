package com.doublexman.common.auth.filter;


import com.doublexman.common.auth.AppAuthProvider;
import com.doublexman.common.auth.UserAuthProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JWTFilter extends GenericFilterBean {

    private AppAuthProvider appAuthProvider;

    private UserAuthProvider userAuthProvider;

    public static final String AUTHORIZATION_TYPE = "AUTH_TYPE";

    public static final String AUTHORIZATION_TOKEN = "AUTH_TOKEN";

    public enum AuthType {
        APP, USER
    }

    public JWTFilter(AppAuthProvider appAuthProvider, UserAuthProvider userAuthProvider) {
        this.appAuthProvider = appAuthProvider;
        this.userAuthProvider = userAuthProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authType = request.getHeader(AUTHORIZATION_TYPE);
        if (null != authType && !authType.isEmpty()) {
            String token = request.getHeader(AUTHORIZATION_TOKEN);
            if (null != token && !token.isEmpty()) {
                Authentication authentication = null;
                if (AuthType.APP.toString().equals(authType) && appAuthProvider.validateToken(token)) {
                    authentication = this.appAuthProvider.getAuthentication(token);
                } else if (AuthType.USER.toString().equals(authType) && userAuthProvider.validateToken(token)) {
                    authentication = this.userAuthProvider.getAuthentication(token);
                }
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
