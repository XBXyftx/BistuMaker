package com.bistu.ckkj.mapper;

import com.bistu.ckkj.pojo.Images;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ImagesMapper {

    @Select("select * from Images")
    List<Images> selectAll();


    @Select("insert into images(image_name,image_url, image_type, create_time) values(#{imageName},#{imagesUrl},#{imagesType},now())")
    void insert(Images images);

    @Delete("delete from images where id = #{id}")
    public void deleteById(Integer id);

    @Select("select * from images where image_type = #{imageType}")
    public List<Images> selectByImageType(Integer imageType);


    @Select("select * from images where id =#{id}")
    public Images selectById(Integer id);
}
