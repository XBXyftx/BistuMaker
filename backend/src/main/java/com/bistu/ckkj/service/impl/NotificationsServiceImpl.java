package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.NotificationsMapper;
import com.bistu.ckkj.pojo.Notifications;
import com.bistu.ckkj.service.NotificationsService;
import lombok.RequiredArgsConstructor;
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
