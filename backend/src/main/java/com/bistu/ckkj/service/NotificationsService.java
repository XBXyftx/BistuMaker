package com.bistu.ckkj.service;

import com.bistu.ckkj.pojo.Notifications;

import java.util.List;

public interface NotificationsService {

    //增
    void addNotifications(Notifications notifications);

    //删
    void deleteNotifications(Integer id);

    //改
    void updateNotifications(Notifications notifications);

    //查id
    Notifications selectNotificationsById(Integer id);

    //查全部
    List<Notifications> selectAllNotifications();

    void updateIsRead(Integer id);

    //获取isRead为1的
    List<Notifications> selectIsRead();
}
