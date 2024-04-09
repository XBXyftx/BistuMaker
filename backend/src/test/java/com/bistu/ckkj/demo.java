package com.bistu.ckkj;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class demo {
    public static void main(String[] args) {






        // 创建两个LocalDateTime对象
        LocalDateTime dateTime1 = LocalDateTime.of(2024, 3, 26, 10, 30); // 示例：2024年3月26日 10:30
        LocalDateTime dateTime2 = LocalDateTime.of(2024, 3, 27, 14, 45); // 示例：2024年3月27日 14:45


        LocalDateTime now = LocalDateTime.now();

        // 创建一个格式器来定义输出格式，例如："yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前日期时间并打印
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime);

    }
}
