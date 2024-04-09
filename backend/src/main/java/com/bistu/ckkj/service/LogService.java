package com.bistu.ckkj.service;


import com.bistu.ckkj.pojo.Log;

import java.util.List;

public interface LogService {
    //增
    void insert(Log log);

    //查全部
    List<Log> selectAll();
}
