package com.example.demo.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhoneAlbum {
    private Integer id;
    private String phoneAlbumName;
    private LocalDateTime createTime;//创建时间
}
