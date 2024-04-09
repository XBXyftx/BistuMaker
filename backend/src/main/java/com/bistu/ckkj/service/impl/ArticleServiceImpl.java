package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.ArticleMapper;
import com.bistu.ckkj.pojo.Article;
import com.bistu.ckkj.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
//        System.out.println("Service层"+article.toString());
        articleMapper.insert(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.update(article);
    }

    @Override
    public List<Article> selectArticle() {
        return articleMapper.selectAllArticles();
    }

    @Override
    public Article selectArticleById(Integer id) {


        return articleMapper.selectById(id);
    }

    @Override
    public List<Article> selectArticleByType(String type) {
        return articleMapper.selectByType(type);
    }

    @Override
    public List<Article> selectArticleByTop() {
        return articleMapper.selectByTop();
    }

    @Override
    public void updateTop(Integer id) {
        articleMapper.updateIsRead(id);
    }

    @Override
    public void addVisits(Integer id) {
        articleMapper.addVisits(id);
    }
}
