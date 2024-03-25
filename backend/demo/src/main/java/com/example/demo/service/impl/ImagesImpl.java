package com.example.demo.service.impl;

import com.example.demo.mapper.ImagesMapper;
import com.example.demo.pojo.Images;
import com.example.demo.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagesImpl implements ImagesService {

    private final ImagesMapper imagesMapper;

    @Override
    public void upload(String imagesName, String imagesUrl, String imagesType) {
        imagesMapper.insert(new Images(imagesName,imagesUrl,imagesType));
    }

//    @Override
//    public void upload(String imagesUrl, String imagesType) {
//
//    }

    @Override
    public void delete(Integer id) {
        imagesMapper.deleteById(id);
    }

    @Override
    public List<Images> selectImagesType(Integer imageType) {
        return imagesMapper.selectByImageType(imageType);
    }

    @Override
    public Images selectInfo(Integer id) {
        return imagesMapper.selectById(id);
    }
}
