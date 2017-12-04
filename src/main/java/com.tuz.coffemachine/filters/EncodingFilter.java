package com.tuz.coffemachine.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String encoding = req.getCharacterEncoding();
        if (!"UTF-8".equalsIgnoreCase(encoding))
        {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }



}
