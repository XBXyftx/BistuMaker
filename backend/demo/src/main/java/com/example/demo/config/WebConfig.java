package com.example.demo.config;

import com.example.demo.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

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
                "/images/**"
                );




    }
}
