package com.bistu.ckkj.service;

import com.bistu.ckkj.pojo.PhoneAlbum;

import java.util.List;

public interface PhoneAlbumService {
    //增
    void addPhoneAlbum(String phonealbum);

    //删
    void deletePhoneAlbum(Integer id);

    //查
    PhoneAlbum selectPhoneAlbumById(Integer id);

    //查全部
    List<PhoneAlbum> selectAllPhoneAlbum();

}
