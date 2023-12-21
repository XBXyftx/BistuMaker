package com.example.demo;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest//如果在测试类上添加了这个注解,那么将来单元测试方法执行之前,会先初始化Spring容器
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    @Test
    public void testSet(){
        User u = userService.findByUserName("admin");
        //判断用户是否存在
        if(u==null){
            System.out.println("null");
//            return Result.error("用户名不存在");
        }
        if(Md5Util.getMD5String("123456").equals(u.getPassword())){
            Map<String , Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);
            //将token存入redis
            ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
            operations.set("token:"+u.getId(),token, 1,TimeUnit.HOURS);
//            return Result.success(token);

        }else{
//            return Result.error("密码错误");
        }
    }

    @Test
    public void testGet(){
        userService.register("admin","123456","admin");

    }
}
