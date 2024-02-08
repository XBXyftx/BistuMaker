package com.example.demo.mapper;

import com.example.demo.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    /*
     * 增加评论
     */
    @Insert("insert into comments(content, article_id,create_time,creator_email,ip,nick_name) values(#{content}, #{articleId}, now(),#{creatorEmail},#{ip},#{nickName})")
    void insert(Comment comment);

    /*
     * 查询所有评论
     */
    @Select("select * from comments")
    List<Comment> selectAll();

    /*
     * 查询单个评论
     */
    @Select("select * from comments where id = #{id}")
    Comment selectById(Integer id);

    /*
     * 删除评论
     */
    @Select("delete from comments where id = #{id}")
    void deleteById(Integer id);
    /*
     * 更新评论
     */
    @Select("update comments set content=#{content} where id=#{id}")
    void update(Comment comment);

    /*
    根据文章id查找
     */
    @Select("select * from comments where article_id = #{articleId} order by create_time desc ")
    List<Comment> selectByArticleId(Integer articleId);
}
