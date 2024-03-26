package com.example.demo.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Token {
    private String tokenId;
    private String token;
    private LocalDateTime expirationTime;

}
