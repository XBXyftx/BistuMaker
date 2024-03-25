package com.example.demo.service;


import com.example.demo.pojo.Log;

import java.util.List;

public interface LogService {
    //增
    void insert(Log log);

    //查全部
    List<Log> selectAll();
}
