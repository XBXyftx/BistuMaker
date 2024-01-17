package com.example.demo.mapper;

import com.example.demo.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /*
    查询所有文章
     */
    @Select("select * from articles")
    List<Article> selectAllArticles();
    /*
    查询单个文章
     */
    @Select("select * from articles where id = #{id}")
    Article selectById(Integer id);

    /*
    新增文章
     */
    @Insert("insert into articles(title, content, cover_image,author,create_time) values(#{title}, #{content},#{coverImage},#{author},now())")
    void insert(Article article);

    /*
    修改文章
     */
    @Select("update articles set title=#{title},content=#{content},cover_image=#{coverImage},modify_time=now() where id=#{id}")
    void update(Article article);

    /*
    删除文章
     */
    @Select("delete from articles where id = #{id}")
    void deleteById(Integer id);


}
