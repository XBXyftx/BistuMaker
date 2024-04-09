package com.bistu.ckkj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Log {
    private Integer id;
    private String message; //消息
    private String type; //类型
    private String ip; //ip地址
    private LocalDateTime createTime; // 创建时间



    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}