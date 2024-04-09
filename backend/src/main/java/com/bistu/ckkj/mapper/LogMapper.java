package com.bistu.ckkj.mapper;

import com.bistu.ckkj.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    @Insert("insert into log(message,ip,type, create_time) values(#{message},#{ip},#{type},now())")
    void insert(Log log);

    @Select("select * from log")
    List<Log> selectAll();
}