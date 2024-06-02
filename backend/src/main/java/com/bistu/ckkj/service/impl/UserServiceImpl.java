package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.UserMapper;
import com.bistu.ckkj.pojo.User;
import com.bistu.ckkj.utils.Md5Util;
import com.bistu.ckkj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User findByUserName(String username) {

        return userMapper.selectByUsername(username);
    }

    @Override
    public void register(String username, String password,String role) {
        String md5PwdStr = Md5Util.getMD5String(password);
        User user = new User(username,md5PwdStr,role);
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        User user1 = userMapper.selectByUsername(user.getUsername());


        user.setId(user1.getId());
        user.setPassword(Md5Util.getMD5String(user.getPassword()));
//        user.setId(user1.getId());
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }
}
