package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.MessagesMapper;
import com.bistu.ckkj.pojo.Messages;
import com.bistu.ckkj.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class MessagesServiceImpl implements MessagesService {
    private final MessagesMapper messagesMapper;

    @Override
    public void insert(Messages message) {
        messagesMapper.insert(message);
    }

    @Override
    public void delete(Integer id) {
        messagesMapper.deleteById(id);
    }

    @Override
    public List<Messages> selectAll() {
        return messagesMapper.selectAll();
    }


}
