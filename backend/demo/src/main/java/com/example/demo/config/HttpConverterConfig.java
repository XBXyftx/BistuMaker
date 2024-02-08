package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HttpConverterConfig implements WebMvcConfigurer {
    @Value("${upload.location.os}")
    String path;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        path=path+'/';
        //其中image表示访问的前缀。"file:F:/img/"是文件真实的存储路径
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);
    }

}
