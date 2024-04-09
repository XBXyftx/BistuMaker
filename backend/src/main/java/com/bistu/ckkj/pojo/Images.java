package com.bistu.ckkj.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Images {
    private Integer id;
    private String imageName;
    private String imagesUrl;
    private String ImagesType;
    private LocalDateTime createTime;//创建时间

    public Images(String imageName,String imagesUrl, String imagesType) {
        this.imageName = imageName;
        this.imagesUrl = imagesUrl;
        this.ImagesType = imagesType;
    }

}
