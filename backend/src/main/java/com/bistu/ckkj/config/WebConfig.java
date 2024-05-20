package com.bistu.ckkj.config;

import com.bistu.ckkj.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Web配置类，实现Spring MVC的WebMvcConfigurer接口，用于自定义配置，
 * 包括拦截器的配置。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    /*
      添加拦截器配置，定义哪些请求需要被拦截，以及哪些请求路径应该被排除在拦截之外。

      @param registry InterceptorRegistry拦截器注册表，用于注册和配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                "/user/login",
                "/user/register",
                "/article/articleType",
                "/article/articleInfo",
                "/comment/add",
                "/comment/commentByArticleId",
                "/upload/img",
                "/enrollment/add",
                "/notifications/isRead",
                "/images/imageType",
                "/phoneAlbum/allInfo",
                "/phoneAlbum/allImage",
                "/images/**",
                "/image/imageType",
                "/article/blogAll",
                "/article/addVisits",
                "/messages/add",
                "/messages/allInfo",
                "/messages/delete"


                );

    }
}
