package com.bistu.ckkj.service;

import com.bistu.ckkj.pojo.Images;

import java.util.List;

public interface ImagesService {

    void upload(String imagesName, String imagesUrl, String imagesType);

//    //上传图片
//    public void upload(String imagesUrl,String imagesType);

    //删除图片
    public void delete(Integer id);

    //查询图片image_type
    public List<Images> selectImagesType(String imageType);

//    public List<Images> selectAll();

    public Images selectInfo(Integer id);
}
