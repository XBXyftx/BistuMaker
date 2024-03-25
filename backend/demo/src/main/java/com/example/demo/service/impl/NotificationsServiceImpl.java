package com.example.demo.service.impl;

import com.example.demo.mapper.NotificationsMapper;
import com.example.demo.pojo.Notifications;
import com.example.demo.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationsServiceImpl implements NotificationsService {


    private final NotificationsMapper notificationsMapper;
    @Override
    public void addNotifications(Notifications notifications) {
        notificationsMapper.insert(notifications);
    }

    @Override
    public void deleteNotifications(Integer id) {
        notificationsMapper.deleteById(id);
    }

    @Override
    public void updateNotifications(Notifications notifications) {
        notificationsMapper.update(notifications);
    }

    @Override
    public Notifications selectNotificationsById(Integer id) {
        return notificationsMapper.selectById(id);
    }

    @Override
    public List<Notifications> selectAllNotifications() {
        return notificationsMapper.selectAll();
    }

    @Override
    public void updateIsRead(Integer id) {
        notificationsMapper.updateIsRead(id);
    }

    @Override
    public List<Notifications> selectIsRead() {
        return notificationsMapper.selectIsRead();
    }
}
