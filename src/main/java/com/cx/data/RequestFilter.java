package com.cx.data;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(value = "/**",filterName = "dbfilter")
public  class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String key = request.getHeader("key");
        ThreadLocalKeyUtil.setDB(key);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        ThreadLocalKeyUtil.clearDB();
    }
}
