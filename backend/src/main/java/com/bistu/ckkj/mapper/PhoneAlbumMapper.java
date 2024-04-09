package com.bistu.ckkj.mapper;

import com.bistu.ckkj.pojo.PhoneAlbum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PhoneAlbumMapper {
    //查
    @Select("select * from phonealbum order by create_time desc ")
    List<PhoneAlbum> selectAllPhoneAlbum();

    //根据id查询
    @Select("select * from phonealbum where id = #{id}")
    PhoneAlbum selectPhoneAlbumById(Integer id);

    //增加
    @Insert("insert into phonealbum (phone_album_name,create_time) values (#{phoneAlbumName},now())")
    void insertPhoneAlbum(String phoneAlbum);

    //删
    @Delete("delete from phonealbum where id = #{id}")
    void deletePhoneAlbum(Integer id);


}
