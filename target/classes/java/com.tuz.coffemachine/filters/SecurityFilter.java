package com.tuz.coffemachine.filters;

import com.tuz.coffemachine.resources.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SecurityFilter implements javax.servlet.Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("errroooorororororoorr");
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("2");
        req.getRequestDispatcher(ConfigurationManager.getProperty("path.page.error")).forward(request, response);
        System.out.println("3");
        req.getSession().invalidate();
    }

    @Override
    public void destroy() {
    }

}

