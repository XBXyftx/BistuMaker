package com.bistu.ckkj.controller;

import com.bistu.ckkj.pojo.Token;
import com.bistu.ckkj.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;
    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);

//    检车token是否过期，每一小时检查下
    @Scheduled(fixedRate = 1000 * 60 * 60 )
    public void checkToken() {
        System.out.println("token过期检查");
        List<Token> tokens = tokenService.selectAllToken();
        if (tokens.isEmpty()){
            logger.info("当前没有有效的token");
            return;
        }
        // 获取当前时间（基于系统默认时区）
        LocalDateTime now = LocalDateTime.now();
        int expiredCount = 0;
        for(Token token : tokens){
            if (now.isAfter(token.getExpirationTime())) {
                tokenService.deleteToken(token.getToken());
                logger.info("Token [{}] 已过期并被删除", token.getToken());
                expiredCount++;
            }
        }
        logger.info("本次检查共发现 {} 个过期的token", expiredCount);
    }


}
