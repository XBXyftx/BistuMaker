package com.example.demo.service.impl;

import com.example.demo.mapper.LogMapper;
import com.example.demo.pojo.Log;
import com.example.demo.service.LogService;
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
