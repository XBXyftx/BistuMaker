package com.bistu.ckkj.service;

import com.bistu.ckkj.pojo.Article;

import java.util.List;

public interface ArticleService {
    //增
    void addArticle(Article article);

    //删
    void deleteArticle(Integer id);

    //改
    void updateArticle(Article article);

    //查全部
    List<Article> selectArticle();

    //查
    Article selectArticleById(Integer id);

    //根据type查询
    List<Article> selectArticleByType(String type);

    //查询置顶文章
    List<Article> selectArticleByTop();


    //修改置顶
    void updateTop(Integer id);

    void addVisits(Integer id);
}
