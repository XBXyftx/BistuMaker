package com.example.demo.service;

public interface ImagesService {

    //上传图片
    public void upload(String imagesUrl,String imagesType);

    //删除图片
    public void delete(Integer id);
}
