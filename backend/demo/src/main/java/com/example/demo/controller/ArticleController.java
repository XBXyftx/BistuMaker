package com.example.demo.controller;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.Result;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public Result addArticle(@RequestBody Article article){
        System.out.println("接口层测试"+article.toString());
        articleService.addArticle(article);
        return Result.success("添加成功");
    }

    @GetMapping("/articleInfo")
    public Result selectArticleById(Integer id){
        return Result.success(articleService.selectArticleById(id));

    }
    @GetMapping("/articleAllInfo")
    public Result selectAllArticle(){
        return Result.success(articleService.selectArticle());
    }

    //根据type值查询
    @GetMapping("/articleType")
    public Result selectArticleByType(String type){
        return Result.success(articleService.selectArticleByType(type));
    }

    @PutMapping("/update")
    public Result updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success();
    }

    //删除
    @DeleteMapping("/delete")
    public Result deleteArticle( Integer id){

        articleService.deleteArticle(id);
        return Result.success();
    }


}
