package com.example.demo.controller;

import com.example.demo.aop.MyLog;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Md5Util;
import com.example.demo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@RestController//支持自动将接口返回的数据类型转换为JSON格式
@Validated//它可以在保存实体类之前对其进行验证，并根据指定的验证规则进行校验。该注解可用于字段、集合、实体类等。
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;


    //注册
    @MyLog(value = "注册")
    @PostMapping("/register")
    public Result register (@Pattern(regexp = "^\\S{5,16}$") String username,
                            @Pattern(regexp = "^\\S{5,16}$") String password,
                            String role){
        //查询用户
        User u = userService.findByUserName(username);
        if(u!=null){
            return Result.error("用户名已存在");
        }else{
            if(role==null){
                role="user";
            }
            userService.register(username,password,role);
            return Result.success();
        }
    }
    //索引全部用户
    @MyLog(value = "查询全部用户")
    @GetMapping
    public Result<List<User>> selectAllUser(){
        return Result.success(userService.selectAllUser());
    }

    //删除用户
    @MyLog(value = "删除用户")
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return Result.success();
    }
    //登录
    @MyLog(value = "登录")
    @PostMapping("/login")
    public Result login (@Pattern(regexp = "^\\S{3,16}$") String username,
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
//    @MyLog(value = "用户信息")
    @GetMapping("/userInfo")
    public Result userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        //根据用户名查询用户
       /* Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        if (map == null){
            return Result.error("请先登录");
        }
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        System.out.println(user);
        return Result.success(user);
    }


    @MyLog(value = "查询全部用户")
    @GetMapping("/userAllInfo")
    public Result<List<User>> userAllInfo() {
        //查询所有用户表
        List<User> user = userService.selectAllUser();
        System.out.println("123"+Arrays.toString(user.toArray()));
        return Result.success(user);

    }

    @MyLog(value = "更新用户")
    @PutMapping("/update")
    public Result update(@Validated User user) {
//        System.out.println("密码为:"+user.getPassword());
        userService.updateUser(user);
        return Result.success();
    }



}
