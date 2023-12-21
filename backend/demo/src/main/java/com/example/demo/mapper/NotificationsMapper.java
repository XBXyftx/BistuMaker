package com.example.demo.mapper;

import com.example.demo.pojo.Notifications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public  interface NotificationsMapper {
    /*
     * 查询所有通知
     */
    @Select("select * from notifications")
    List<Notifications> selectAll();

    /*
     * 删除通知
     */
    @Select("delete from notifications where id = #{id}")
    void deleteById(Integer id);

    /*
     * 增加通知
     */
    @Select("insert into notifications(title,content,create_time) values(#{title}, #{content}, now())")
    void insert(Notifications notifications);

    /*
     * 修改通知
     */
    @Select("update notifications set title=#{title},content=#{content} where id=#{id}")
    void update(Notifications notifications);

}
