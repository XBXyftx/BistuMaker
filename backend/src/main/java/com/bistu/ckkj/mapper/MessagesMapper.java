package com.bistu.ckkj.mapper;

import com.bistu.ckkj.pojo.Messages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface MessagesMapper {

    //查
    @Select("select * from messages")
    List<Messages> selectAll();
    //增
    @Insert("insert into messages(username,message,ip,address,os,browser) values(#{username},#{message},#{ip},#{address},#{os},#{browser})")
    void insert(Messages message);
    //删
    @Delete("delete from messages where id = #{id}")
    void deleteById(Integer id);




}
