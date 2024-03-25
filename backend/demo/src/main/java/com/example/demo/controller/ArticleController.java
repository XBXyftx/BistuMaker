package com.example.demo.controller;

import com.example.demo.aop.MyLog;
import com.example.demo.pojo.Article;
import com.example.demo.pojo.Result;
import com.example.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    @MyLog(value = "添加文章")  //这里添加了AOP的自定义注解
    @PostMapping("/add")
    public Result addArticle(@RequestBody Article article){
        System.out.println("接口层测试"+article.toString());
        articleService.addArticle(article);
        return Result.success("添加成功");
    }
    @MyLog(value = "根据ID查询文章")  //这里添加了AOP的自定义注解
    @GetMapping("/articleInfo")
    public Result selectArticleById(Integer id){
        return Result.success(articleService.selectArticleById(id));

    }
    @MyLog(value = "查询所有文章")  //这里添加了AOP的自定义注解
    @GetMapping("/articleAllInfo")
    public Result selectAllArticle(){
        return Result.success(articleService.selectArticle());
    }

    //根据type值查询
    @MyLog(value = "根据TYPE查询文章")  //这里添加了AOP的自定义注解
    @GetMapping("/articleType")
    public Result selectArticleByType(String type){
        return Result.success(articleService.selectArticleByType(type));
    }

    @MyLog(value = "更新文章")  //这里添加了AOP的自定义注解
    @PutMapping("/update")
    public Result updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success();
    }

    //删除
    @MyLog(value = "删除文章")  //这里添加了AOP的自定义注解
    @DeleteMapping("/delete")
    public Result deleteArticle( Integer id){

        articleService.deleteArticle(id);
        return Result.success();
    }


}
