package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Token {
    private String tokenId;
    private String token;
    private LocalDateTime expirationTime;
    private String userName;


}
