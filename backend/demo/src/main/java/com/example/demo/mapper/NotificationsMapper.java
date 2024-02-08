package com.example.demo.mapper;

import com.example.demo.pojo.Notifications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public  interface NotificationsMapper {
    /*
     * 查询所有通知
     */
    @Select("select * from notifications")
    List<Notifications> selectAll();

    /*
     * 根据id查询通知
     */
    @Select("select * from notifications where id = #{id}")
    Notifications selectById(Integer id);
    /*
     * 删除通知
     */
    @Select("delete from notifications where id = #{id}")
    void deleteById(Integer id);

    /*
     * 增加通知
     */
    @Select("insert into notifications(title,content,create_time,is_read) values(#{title}, #{content}, now(),#{isRead})")
    void insert(Notifications notifications);

    /*
     * 修改通知
     */
    @Select("update notifications set title=#{title},content=#{content} where id=#{id}")
    void update(Notifications notifications);


    /*
     * 修改isRead  取反
     */
    void updateIsRead(Integer id);


    /*
    * 获取isRead为1的
     */
    @Select("select * from notifications where is_read = 1")
    List<Notifications> selectIsRead();
}
