package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Md5Util;
import com.example.demo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@RestController//支持自动将接口返回的数据类型转换为JSON格式
@Validated//它可以在保存实体类之前对其进行验证，并根据指定的验证规则进行校验。该注解可用于字段、集合、实体类等。
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //注册
    @PostMapping("/register")
    public Result register (@Pattern(regexp = "^\\S{5,16}$") String username,
                            @Pattern(regexp = "^\\S{5,16}$") String password,
                            String role){
        //查询用户
        User u = userService.findByUserName(username);
        if(u!=null){
            return Result.error("用户名已存在");
        }else{
            if(role==null && role.equals("")){
                role="user";
            }
            userService.register(username,password,role);
            return Result.success();
        }
    }
    //索引全部用户
    @GetMapping
    public Result selectAllUser(){
        return Result.success(userService.selectAllUser());
    }

    //删除用户
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return Result.success();
    }
    //登录
    @PostMapping("/login")
    public Result<String> login (@Pattern(regexp = "^\\S{3,16}$") String username,
                                 @Pattern(regexp = "^\\S{5,16}$") String password
                                 ){

        System.out.println("username+"+username+"password+"+password);
        //查询用户
        User u = userService.findByUserName(username);
        //判断用户是否存在
        if(u==null){
            return Result.error("用户名不存在");
        }
        if(Md5Util.getMD5String(password).equals(u.getPassword())){
            Map<String , Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);
            //将token存入redis
            ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token, 1,TimeUnit.HOURS);
            return Result.success(token);

        }else{
            return Result.error("密码错误");
        }

    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        //根据用户名查询用户
       /* Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @GetMapping("/userAllInfo")
    public Result<User> userAllInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        //根据用户名查询用户
       /* Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }



}
