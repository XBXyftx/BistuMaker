package com.bistu.ckkj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;
/**
 * JWT工具类，用于生成和解析Token
 */
public class JwtUtil {

    private static final String KEY = "club";

    /**
     * 根据传入的业务数据生成Token
     *
     * @param claims 业务数据，以Map形式存储，将被嵌入到Token中
     * @return 生成的Token字符串
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 100 ))
                .sign(Algorithm.HMAC256(KEY));
    }

    /**
     * 解析Token并返回其中的业务数据
     *
     * @param token 需要解析的Token字符串
     * @return 解析出的业务数据Map
     */
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
