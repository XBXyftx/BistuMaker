package com.bistu.ckkj.service;

import com.bistu.ckkj.pojo.Comment;

import java.util.List;

public interface CommentService {
    //增
    void addComment(Comment comment);

    //删
    void deleteComment(Integer id);

    //改
    void updateComment(Comment comment);

    //根据id查
    Comment selectCommentById(Integer id);

    //查所有
    List<Comment> selectAllComment();

    //根据发帖id查询
    List<Comment> selectCommentByArticleId(Integer articleId);
}
