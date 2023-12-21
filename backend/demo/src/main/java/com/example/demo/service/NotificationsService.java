package com.example.demo.service;

import com.example.demo.pojo.Notifications;

import java.util.List;

public interface NotificationsService {

    //增
    void addNotifications(Notifications notifications);

    //删
    void deleteNotifications(Integer id);

    //改
    void updateNotifications(Notifications notifications);

    //查
    List<Notifications> selectNotificationsById();
}
