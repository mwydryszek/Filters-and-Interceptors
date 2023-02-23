package com.example.filter.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class HeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(httpServletResponse);

        String requestId = UUID.randomUUID().toString();

        resp.addHeader("REQUEST_ID", requestId);

        chain.doFilter(req, resp);

        resp.copyBodyToResponse();

    }
}
