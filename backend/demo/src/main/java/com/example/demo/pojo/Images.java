package com.example.demo.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Images {
    private Integer id;
    private String imagesUrl;
    private String ImagesType;
    private LocalDateTime createTime;//创建时间

    public Images(String imagesUrl, String imagesType) {
        this.imagesUrl = imagesUrl;
        this.ImagesType = imagesType;
    }

}
