package com.example.demo.controller;

import com.example.demo.aop.MyLog;
import com.example.demo.pojo.Result;

import com.example.demo.service.ImagesService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
public class uploadController {

    /**
     * 时间格式化
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    private final ImagesService imagesService;

    @Value("${upload.location.os}")
    String path;
    @Value("${spring.profiles.active}")
    String os;


    @MyLog(value = "添加照片")
    @PostMapping("/img")
    public Result upload(@RequestParam("file") MultipartFile file, String imageType,String imageName , HttpServletRequest request) throws IOException {
        System.out.println(imageType);
        System.out.println(imageName);
//        String directory = simpleDateFormat.format(new Date());
//        System.out.println(directory);
//
//        if (file == null) {
//            return Result.error("上传失败");
//        }
//
//        //文件名字
//        String fileName = file.getOriginalFilename();
////        String filePath = "G:\\项目\\社团官网\\源代码\\backend\\demo\\target\\classes\\static\\images\\";
//        String filePath = ResourceUtils.getURL("").getPath() + "static/images/";
//        filePath = java.net.URLDecoder.decode(filePath, "utf-8");
//        System.out.println("filepath目录为"+filePath);
//        System.out.println(filePath + directory + fileName);
//        File uploadFile = new File(filePath+directory);
//        uploadFile.mkdir();
//        if (!uploadFile.exists()) {
//            System.out.println("文件不存在");
//            uploadFile.getParentFile().mkdirs();
//            uploadFile.mkdirs();
//        }
//
//        try{
//            file.transferTo(new File(filePath +directory+fileName ));
//        }catch (Exception e ){
//            return Result.error("上传失败");
//        }
//        String basePath = request.getScheme()+"://" +
//                request.getServerName() + ":" + request.getServerPort() +
//                request.getContextPath() + "/";
//        String ImagesUrl =("/images/"+directory+fileName);
//        System.out.println("图片路径：" + ImagesUrl);
//
//
////        imagesService.upload(fileName,ImagesUrl,"封面");
//        return Result.success(ImagesUrl);


        System.out.println("os为"+os);
        String directory = simpleDateFormat.format(new Date());
//        System.out.println(directory);
        if (file == null) {
            return Result.error("上传失败");
        }
//文件名字
        String fileName = file.getOriginalFilename();//xxx.jpg
        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid.toString());
        String extension = fileName.substring(fileName.lastIndexOf(".")); // 获取 ".jpg"
        fileName = uuid.toString() + extension;
        path=path+'/';
        System.out.println("path+"+path);
        System.out.println(path+directory+fileName);
        File uploadFile = new File(path+directory+fileName);
        uploadFile.mkdir();
        if (!uploadFile.exists()) {
            System.out.println("文件不存在");
            uploadFile.getParentFile().mkdirs();
            uploadFile.mkdirs();
        }

        try{
            file.transferTo(uploadFile);
        }catch (Exception e ){
            return Result.error("上传失败");
        }

        System.out.println(directory+fileName);


        if(imageName!=null){
            if(imageType!=null){
                imagesService.upload(imageName,"/images/"+directory+fileName,imageType);
                return Result.success("/images/"+directory+fileName);
            }
        }
        imagesService.upload(fileName,"/images/"+directory+fileName,"封面");
        return Result.success("/images/"+directory+fileName);

    }


}
