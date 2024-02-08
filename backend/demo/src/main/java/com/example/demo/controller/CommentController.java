package com.example.demo.controller;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.Result;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Result addComment(@RequestBody  Comment comment){
        System.out.println(comment.toString());
        commentService.addComment(comment);
        return Result.success("添加成功");
    }

    //根据文章id查询评价
    @GetMapping("/commentByArticleId")
    public Result selectCommentByArticleId(Integer id){
        return Result.success(commentService.selectCommentByArticleId(id));
    }


    //查询全部评价
    @GetMapping("/commentAll")
    public Result selectAllComment(){
        return Result.success(commentService.selectAllComment());
    }

    //根据id删除评价
    @GetMapping("/delete")
    public Result deleteComment(Integer id){
        commentService.deleteComment(id);
        return Result.success("删除成功");
    }


//    @GetMapping("/commentById")
//    public Result selectCommentById(Integer id){
//        return Result.success(commentService.selectCommentByArticleId(id));
//    }

}
