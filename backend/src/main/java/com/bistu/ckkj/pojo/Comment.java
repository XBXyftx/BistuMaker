package com.bistu.ckkj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id; // ID
    private String content; // 评论内容
    private int articleId; // 文章ID
    private int creatorId; // 创建人ID

    private String creatorEmail;

    private String ip;
    private LocalDateTime createTime; // 创建时间

    private String nickName;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", articleId=" + articleId +
                ", creatorId=" + creatorId +
                ", creatorEmail='" + creatorEmail + '\'' +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    // Getters and Setters
}
