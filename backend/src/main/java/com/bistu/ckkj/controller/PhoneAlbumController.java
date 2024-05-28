package com.bistu.ckkj.controller;

import com.bistu.ckkj.pojo.Images;
import com.bistu.ckkj.pojo.PhoneAlbum;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.PhoneAlbumService;
import com.bistu.ckkj.service.ImagesService;
import lombok.RequiredArgsConstructor;
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
            images.addAll(imagesService.selectImagesType(String.valueOf(id)));
            if (images.size() > 10){
                return Result.success(images);
            }
        }
        return Result.success(images);
    }

    @DeleteMapping("/delete")
    public Result deletePhoneAlbum(Integer id) {
        phoneAlbumService.deletePhoneAlbum(id);
        return Result.success("删除成功");
    }





}
