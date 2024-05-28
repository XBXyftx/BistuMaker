package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.ImagesMapper;
import com.bistu.ckkj.pojo.Images;
import com.bistu.ckkj.service.ImagesService;
import lombok.RequiredArgsConstructor;
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
    public List<Images> selectImagesType(String imageType) {
        return imagesMapper.selectByImageType((imageType));
    }

    @Override
    public Images selectInfo(Integer id) {
        return imagesMapper.selectById(id);
    }
}
