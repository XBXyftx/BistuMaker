package com.bistu.ckkj.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * HttpContextUtils类提供了用于获取当前HTTP请求的工具方法。
 */
public class HttpContextUtils {

    /**
     * 获取当前线程中的HttpServletRequest对象。
     *
     * @return 当前HTTP请求的HttpServletRequest对象。
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}