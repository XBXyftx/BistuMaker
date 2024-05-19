package com.bistu.ckkj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Messages {
    private int id;
    private String username;
    private String message;
    private String ip;
    private String address;
    private String os;
    private String browser;
    private LocalDateTime createTime;
}
