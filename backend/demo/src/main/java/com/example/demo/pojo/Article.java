package com.example.demo.pojo;

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
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime  modifyTime; // 修改时间
    private String type;
    private String synopsis;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                '}';
    }
    // Getters and Setters
}
