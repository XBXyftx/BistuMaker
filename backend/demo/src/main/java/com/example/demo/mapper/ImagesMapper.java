package com.example.demo.mapper;

import com.example.demo.pojo.Images;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ImagesMapper {

    @Select("select * from Images")
    public List<Images> selectAll();


    @Select("insert into images(image_url, image_type, create_time) values(#{imagesUrl},#{imagesType},now())")
    public void insert(Images images);

    @Select("delete from images where id = #{id}")
    public void deleteById(Integer id);

}
