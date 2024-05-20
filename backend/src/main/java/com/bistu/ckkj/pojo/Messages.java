package com.bistu.ckkj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
}
