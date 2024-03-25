package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//配置类
@Configuration
public class HttpConverterConfig implements WebMvcConfigurer {

    @Value("${upload.location.os}")//注解用于注入一个属性值，这个属性值由上传位置的操作系统指定。
    String path;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        System.out.println(System.getProperty("user.dir"));
        path=path+'/';
        //其中image表示访问的前缀。"file:F:/img/"是文件真实的存储路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+path);
    }

}
