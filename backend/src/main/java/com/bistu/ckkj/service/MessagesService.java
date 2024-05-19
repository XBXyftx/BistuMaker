package com.bistu.ckkj.service;

import com.bistu.ckkj.pojo.Messages;

import java.util.List;

public interface MessagesService {


    //增
    void insert(Messages message);

    //删
    void delete(Integer id);

    //查
    List<Messages> selectAll();
}
