package com.example.filter.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthorizationCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

//      ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(httpServletRequest);
//      ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(httpServletResponse);

        String header = httpServletRequest.getHeader("Authorization");

        if (header == null || header.isEmpty()) {
            //httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(httpServletRequest, httpServletResponse);

        //resp.copyBodyToResponse();

    }
}
