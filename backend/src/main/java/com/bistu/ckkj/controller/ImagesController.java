package com.bistu.ckkj.controller;

import com.bistu.ckkj.aop.MyLog;
import com.bistu.ckkj.pojo.Images;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImagesController {

    private final ImagesService imagesService;


    @Value("${upload.location.os1}")
    String path;

    @MyLog(value = "")
    @GetMapping("/imageType")
    public Result selectImagesByImagesType(Integer imageType) {
        return Result.success(imagesService.selectImagesType(imageType));
    }

//    @GetMapping("/allInfo")
//    public Result selectAllImages() {
//        return Result.success(imagesService.selectAll());
//    }

    @MyLog(value = "删除图片")
    @DeleteMapping("/delete")
    public Result deleteImages(Integer id) {

        Images images = imagesService.selectInfo(id);
        if (images == null){
            return Result.error("该图片不存在");
        }
//        System.out.println(path+images.getImagesUrl());
        File file = new File(path+images.getImagesUrl());
        if (file.exists()){
            file.delete();
            imagesService.delete(id);
            return Result.success("操作图片成功");
        }else{
            imagesService.delete(id);
            return Result.success("删除异常图片");
        }

    }

    @GetMapping("/info")
    public Result selectInfo(Integer id){
       return Result.success(imagesService.selectInfo(id));
    }

}
