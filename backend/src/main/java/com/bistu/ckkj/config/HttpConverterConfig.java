package com.bistu.ckkj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//配置类

/**
 * HttpConverterConfig类用于配置静态资源路径，使之能够通过指定前缀访问硬盘上的资源。
 */
@Configuration
public class HttpConverterConfig implements WebMvcConfigurer {

    @Value("${upload.location.os}")//注解用于注入一个属性值，这个属性值由上传位置的操作系统指定。
    String path;

    /**
     * 添加资源处理器，配置静态资源访问路径。
     *
     * @param registry 资源处理器注册表，用于注册和管理资源处理器。
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        System.out.println(System.getProperty("user.dir"));
        path=path+'/';
        //其中image表示访问的前缀。"file:F:/img/"是文件真实的存储路径
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);

        registry.addResourceHandler("/image/**").addResourceLocations("file:"+path);
    }

}
