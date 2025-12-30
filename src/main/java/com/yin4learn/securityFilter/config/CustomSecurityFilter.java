package com.yin4learn.securityFilter.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Order(1)
@Component
public class CustomSecurityFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomSecurityFilter.class);
    private static final String REQUEST_ID_KEY = "requestId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID_KEY, requestId);
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String method = request.getMethod();

            if ("GET".equalsIgnoreCase(method) || "POST".equalsIgnoreCase(method)) {
                logger.info("CustomSecurityFilter is invoked. Request ID: {}", requestId);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                logger.warn("Method {} not allowed. Request ID: {}", method, requestId);
                response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                response.getWriter().write("Method Not Allowed");
            }
        } finally {
            MDC.remove(REQUEST_ID_KEY);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}