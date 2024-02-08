package com.example.demo.mapper;

import com.example.demo.pojo.PhoneAlbum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PhoneAlbumMapper {
    //查
    @Select("select * from phoneAlbum order by create_time desc ")
    List<PhoneAlbum> selectAllPhoneAlbum();

    //根据id查询
    @Select("select * from phoneAlbum where id = #{id}")
    PhoneAlbum selectPhoneAlbumById(Integer id);

    //增加
    @Select("insert into phoneAlbum (phone_album_name,create_time) values (#{phoneAlbumName},now())")
    void insertPhoneAlbum(String phoneAlbum);

    //删
    @Select("delete from phoneAlbum where id = #{id}")
    void deletePhoneAlbum(Integer id);


}
