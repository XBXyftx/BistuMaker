package com.example.demo.service.impl;

import com.example.demo.mapper.PhoneAlbumMapper;
import com.example.demo.pojo.PhoneAlbum;
import com.example.demo.service.PhoneAlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneAlbumServiceImpl implements PhoneAlbumService {


    private final PhoneAlbumMapper phoneAlbumMapper;

    public void addPhoneAlbum(String phoneAlbum) {
        phoneAlbumMapper.insertPhoneAlbum(phoneAlbum);
    }

    public void deletePhoneAlbum(Integer id) {
        phoneAlbumMapper.deletePhoneAlbum(id);
    }

    public PhoneAlbum selectPhoneAlbumById(Integer id) {
        return phoneAlbumMapper.selectPhoneAlbumById(id);
    }

    public List<PhoneAlbum> selectAllPhoneAlbum() {
        return phoneAlbumMapper.selectAllPhoneAlbum();
    }

}
