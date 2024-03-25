package com.example.demo.controller;

import com.example.demo.pojo.Images;
import com.example.demo.pojo.PhoneAlbum;
import com.example.demo.pojo.Result;
import com.example.demo.service.ImagesService;
import com.example.demo.service.PhoneAlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/phoneAlbum")
@RequiredArgsConstructor
public class PhoneAlbumController {

    private final PhoneAlbumService phoneAlbumService;
    private final ImagesService imagesService;


    @GetMapping("/add")
    public Result addPhoneAlbum(String phoneAlbum) {
        System.out.println(phoneAlbum);
        phoneAlbumService.addPhoneAlbum(phoneAlbum);
        return Result.success("添加成功");
    }

    @GetMapping("/info")
    public Result selectPhoneAlbumById(Integer id) {
        PhoneAlbum phoneAlbum = phoneAlbumService.selectPhoneAlbumById(id);
        return Result.success(phoneAlbum);
    }

    @GetMapping("/allInfo")
    public Result selectAllPhoneAlbum() {
        return Result.success(phoneAlbumService.selectAllPhoneAlbum());
    }

    @GetMapping("/allImage")
    public Result selectAllImage() {
        List<Images> images =  new ArrayList<>();
        for (PhoneAlbum phoneAlbum : (phoneAlbumService.selectAllPhoneAlbum())) {
            int id = phoneAlbum.getId();
            images.addAll(imagesService.selectImagesType(id));
            if (images.size() > 10){
                return Result.success(images);
            }
        }
        return Result.success(images);

    }





}
