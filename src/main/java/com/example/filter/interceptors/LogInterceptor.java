package com.example.filter.interceptors;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ContentCachingRequestWrapper req = (ContentCachingRequestWrapper) request;

        log.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());
        log.info("request body = {}", new String(req.getInputStream().readAllBytes(), StandardCharsets.UTF_8));

        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        ContentCachingResponseWrapper resp = (ContentCachingResponseWrapper) response;

        byte[] responseBody = resp.getContentAsByteArray();

        log.info("Logging Response: {} ({})", response.getContentType(), response.getStatus());
        log.info("response body = {}", new String(responseBody, StandardCharsets.UTF_8));
        log.info("Logging REQUEST_ID: {}", response.getHeader("REQUEST_ID"));

    }

}
