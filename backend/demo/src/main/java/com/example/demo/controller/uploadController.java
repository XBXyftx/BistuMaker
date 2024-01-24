package com.example.demo.controller;

import com.example.demo.pojo.Result;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@RequestMapping("/upload")
@RestController//支持自动将接口返回的数据类型转换为JSON格式
@Validated//它可以在保存实体类之前对其进行验证，并根据指定的验证规则进行校验。该注解可用于字段、集合、实体类等。
public class uploadController {

    /**
     * 时间格式化
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/img")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {

        String directory = simpleDateFormat.format(new Date());
        System.out.println(directory);

        if (file == null) {
            return Result.error("上传失败");
        }

        //文件名字
        String fileName = file.getOriginalFilename();
//        String filePath = "G:\\项目\\社团官网\\源代码\\backend\\demo\\target\\classes\\static\\images\\";
        String filePath = ResourceUtils.getURL("classpath:").getPath() + "static/images/";
        filePath = java.net.URLDecoder.decode(filePath, "utf-8");

        System.out.println(filePath + directory + fileName);
        File uploadFile = new File(filePath+directory);
        uploadFile.mkdir();
        if (!uploadFile.exists()) {
            System.out.println("文件不存在");
            uploadFile.getParentFile().mkdirs();
            uploadFile.mkdirs();
        }

        try{
            file.transferTo(new File(filePath +directory+fileName ));
        }catch (Exception e ){
            return Result.error("上传失败");
        }

        return Result.success("上传成功");

    }


}
