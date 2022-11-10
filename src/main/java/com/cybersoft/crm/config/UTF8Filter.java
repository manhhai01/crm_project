package com.cybersoft.crm.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class UTF8Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        if(response.getCharacterEncoding() == null)
            response.setCharacterEncoding("UTF-8");
    }
}
