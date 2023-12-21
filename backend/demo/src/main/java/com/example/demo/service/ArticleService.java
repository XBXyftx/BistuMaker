package com.example.demo.service;

import com.example.demo.pojo.Article;

import java.util.List;

public interface ArticleService {
    //增
    void addArticle(Article article);

    //删
    void deleteArticle(Integer id);

    //改
    void updateArticle(Article article);

    //查全部
    List<Article> selectArticleById();

    //查
    Article selectArticleById(Integer id);
}
