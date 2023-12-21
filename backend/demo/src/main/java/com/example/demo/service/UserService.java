package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {

    //查询全部用户
    List<User> selectAllUser();

    //根据用户名查询用户
    User findByUserName(String username);

    //注册
    void register(String username,String password,String role);

    //修改
    void updateUser(User user);

    //删除
    void deleteUser(Integer id);
}
