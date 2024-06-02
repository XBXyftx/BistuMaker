package com.bistu.ckkj.controller;

import com.bistu.ckkj.aop.MyLog;
import com.bistu.ckkj.pojo.Comment;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @MyLog(value = "添加评论")  //这里添加了AOP的自定义注解
    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment){

        commentService.addComment(comment);
        return Result.success("添加成功");
    }

    //根据文章id查询评价
    @MyLog(value = "根据id查询评价")  //这里添加了AOP的自定义注解
    @GetMapping("/commentByArticleId")
    public Result selectCommentByArticleId(Integer id){
        return Result.success(commentService.selectCommentByArticleId(id));
    }


    //查询全部评价
    @MyLog(value = "查询全部评价")  //这里添加了AOP的自定义注解
    @GetMapping("/commentAll")
    public Result selectAllComment(){
        return Result.success(commentService.selectAllComment());
    }

    //根据id删除评价
    @MyLog(value = "根据id删除评价")  //这里添加了AOP的自定义注解
    @GetMapping("/delete")
    public Result deleteComment(Integer id){
        commentService.deleteComment(id);
        return Result.success("删除成功");
    }



}
