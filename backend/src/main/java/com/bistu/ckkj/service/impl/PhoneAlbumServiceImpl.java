package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.PhoneAlbumMapper;
import com.bistu.ckkj.pojo.PhoneAlbum;
import com.bistu.ckkj.service.PhoneAlbumService;
import lombok.RequiredArgsConstructor;
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
