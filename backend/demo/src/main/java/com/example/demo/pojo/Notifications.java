package com.example.demo.pojo;

import java.time.LocalDateTime;
import lombok.Data;
@Data
public class Notifications {
    private Integer id;
    private String content;
    private String title;
    private LocalDateTime createTime;//创建时间
    private Integer isRead;

    @Override
    public String toString() {
        return "Notifications{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +

                '}';
    }
}
