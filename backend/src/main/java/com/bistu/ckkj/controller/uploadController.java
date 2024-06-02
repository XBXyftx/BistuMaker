package com.bistu.ckkj.controller;

import com.bistu.ckkj.aop.MyLog;
import com.bistu.ckkj.pojo.Result;

import com.bistu.ckkj.service.ImagesService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@RequestMapping("/upload")
@RestController//支持自动将接口返回的数据类型转换为JSON格式
@Validated//它可以在保存实体类之前对其进行验证，并根据指定的验证规则进行校验。该注解可用于字段、集合、实体类等。
@RequiredArgsConstructor
public class uploadController {

    /**
     * 时间格式化
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    private final ImagesService imagesService;

    @Value("${upload.location.os}")
    String path;
    @Value("${upload.location.os1}")
    String os;


    @MyLog(value = "添加照片")
    @PostMapping("/img")
    public Result upload(@RequestParam("file") MultipartFile file, String imageType, String imageName, HttpServletRequest request) throws IOException {
        // Step 1: 检查文件是否为空 以及文件大小
        if (file == null) {
            return Result.error("上传失败");
        }
        long fileSize = file.getSize();
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();

        if (file.isEmpty() || file.getSize() > 10 * 1024 * 1024) {
            return Result.error("文件太大了");
        }


        float scale = 0.8F;
        float quality = 0.65F;

        if (file.getSize() < 10 * 1024 * 1024 && file.getSize() > 5 *1024 * 1024) {
            scale = 0.5F;
            quality = 0.4F;
        }

        // Step 2: 图片压缩（使用thumbnailator）
        String directory = simpleDateFormat.format(new Date());
        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String extension = null;
        if (fileName != null) {
            extension = fileName.substring(fileName.lastIndexOf("."));
        }
        fileName = uuid + extension;

        // 压缩后的图片文件路径
        String compressedFilePath = path + "/" + directory + fileName;
        File compressedFile = new File(compressedFilePath);

        // 创建父目录（如果不存在）
        compressedFile.getParentFile().mkdirs();

        try (InputStream inputStream = file.getInputStream(); FileOutputStream outputStream = new FileOutputStream(compressedFile)) {
            Thumbnails.of(inputStream)
                    .scale(scale) // 默认情况下，不缩放图片
                    .outputQuality(quality) // 设置压缩质量（例如，0.7表示70%质量，可按需调整）
                    .toOutputStream(outputStream);
        }

        // Step 3: 服务端上传逻辑
        if (imageName != null && imageType != null) {
            imagesService.upload(imageName, "/images/" + directory + fileName, imageType);
        } else {
            imagesService.upload(fileName, "/images/" + directory + fileName, "封面");
        }
        return Result.success("/images/" + directory + fileName);
    }

}
