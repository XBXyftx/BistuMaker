package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    @Pattern(regexp = "^\\S{1,10}$")


    private String username;
    @JsonIgnore
    private String password;
    private String role;
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    public User(String username, String password,String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
