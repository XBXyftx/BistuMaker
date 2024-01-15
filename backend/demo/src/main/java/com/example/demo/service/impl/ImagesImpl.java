package com.example.demo.service.impl;

import com.example.demo.mapper.ImagesMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Images;
import com.example.demo.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagesImpl implements ImagesService {
    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public void upload(String imagesUrl, String imagesType) {
        imagesMapper.insert(new Images(imagesUrl,imagesType));
    }

    @Override
    public void delete(Integer id) {
        imagesMapper.deleteById(id);
    }
}
