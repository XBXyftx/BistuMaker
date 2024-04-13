package com.bistu.ckkj.interceptors;


import com.bistu.ckkj.pojo.Token;
import com.bistu.ckkj.service.TokenService;
import com.bistu.ckkj.utils.JwtUtil;
import com.bistu.ckkj.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
/**
 * 登录拦截器，用于验证请求中的令牌(Token)有效性，确保只有经过验证的请求才能访问后续资源。
 */
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

//    private final StringRedisTemplate stringRedisTemplate;
    private final TokenService tokenService;
    /**
     * 在处理请求之前执行的拦截器方法。
     *
     * @param request  HttpServletRequest对象，代表客户端的HTTP请求
     * @param response HttpServletResponse对象，代表服务器的HTTP响应
     * @param handler  将要执行的处理器对象
     * @return boolean 返回true表示放行，返回false表示拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
//            //从redis中获取相同的token
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            String redisToken = operations.get(token);
//            if (redisToken==null){
//                //token已经失效了
//                throw new RuntimeException();
//            }
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            //把业务数据存储到ThreadLocal中
//            ThreadLocalUtil.set(claims);
//            //放行
//            return true;

//            使用mysql
            if (token==null){
                //token已经失效了
                throw new RuntimeException();
            }
            Token mysqlToken= tokenService.selectTokenById(token);
            if (mysqlToken==null){
                throw new RuntimeException();
            }
            Map<String, Object> claims = JwtUtil.parseToken(token);
            System.out.println(claims);
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            //放行
            return true;


        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            //不放行
            return false;
        }
    }
    /**
     * 请求处理完成后执行的拦截器方法。
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param handler  处理器对象
     * @param ex       异常对象，如果处理过程中抛出异常则非null
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}