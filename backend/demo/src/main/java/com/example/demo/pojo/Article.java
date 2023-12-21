package com.example.demo.pojo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Article {
    private Integer id; // ID
    private String title; // 文章标题
    private String content; // 文章内容
    private String coverImage; // 文章封面
    private int authorId; // 创建人ID
    private String author;//作者
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime  modifyTime; // 修改时间


    // Getters and Setters
}
