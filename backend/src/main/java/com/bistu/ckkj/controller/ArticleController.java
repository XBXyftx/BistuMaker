package com.bistu.ckkj.controller;

import com.bistu.ckkj.aop.MyLog;
import com.bistu.ckkj.pojo.Article;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    @MyLog(value = "查询置顶文章")
    @GetMapping("/articleTop")
    public Result selectArticleByTop(){
        return Result.success(articleService.selectArticleByTop());
    }


    //博文=置顶文章+类型为文章的所有文章
    @MyLog(value = "查询博文")
    @GetMapping("/blogAll")
    public Result selectArticleAll(){
        List<Article> top = new ArrayList<>();
        List<Article> typeBy = articleService.selectArticleByType("文章");
        for(Article article : typeBy){
            if(article.getTop()==1){
                 top.add(article);
                 typeBy.remove(article);
            }
        }
        top.addAll(typeBy);
        return Result.success( top  );
    }


    //修改置顶
    @MyLog(value = "修改置顶")
    @GetMapping("/updateTop")
    public Result updateTop(Integer id){
        articleService.updateTop(id);
        return Result.success();
    }

    //记录全局变量 用来记录哪个ip在一分钟内访问了多少 我该取什么名字给这个变量


//    private static HashMap<String,Integer> ip= new HashMap<>();

    //添加阅读量
    @MyLog(value="文章阅读量+1")
    @GetMapping("/addVisits")
    public Result addRead(Integer id){


        articleService.addVisits(id);
        return Result.success();
    }



}
