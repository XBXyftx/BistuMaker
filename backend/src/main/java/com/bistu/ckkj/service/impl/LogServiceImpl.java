package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.LogMapper;
import com.bistu.ckkj.pojo.Log;
import com.bistu.ckkj.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }

    @Override
    public List<Log> selectAll() {
        return logMapper.selectAll();
    }
}
