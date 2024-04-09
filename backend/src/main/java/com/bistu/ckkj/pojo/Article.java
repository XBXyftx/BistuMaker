package com.bistu.ckkj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id; // ID
    private String title; // 文章标题
    private String content; // 文章内容
    private String coverImage; // 文章封面
//    private int authorId; // 创建人ID
    private String author;//作者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime; // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime  modifyTime; // 修改时间
    private String type;
    private String synopsis;


    private String label;//标签
    private Integer top;
    private  Integer visits;//访问量


    // Getters and Setters
}
