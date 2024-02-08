package com.example.demo.controller;

import com.example.demo.mapper.NotificationsMapper;
import com.example.demo.pojo.Notifications;
import com.example.demo.pojo.Result;
import com.example.demo.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {
    @Autowired
    private NotificationsService notificationsController;

    @PostMapping("/add")
    public Result addNotifications(@RequestBody Notifications notifications){
        System.out.println(notifications.toString());
        notificationsController.addNotifications(notifications);
        return Result.success("添加成功");
    }

    @GetMapping("/info")
    public Result selectNotificationsById(Integer id){
        return Result.success(notificationsController.selectNotificationsById(id));
    }

    @GetMapping("/allInfo")
    public Result selectAllNotifications(){
        List<Notifications> notifications = notificationsController.selectAllNotifications();
        for (int i=0;i<notifications.size();i++){
            System.out.println("数据为"+notifications.get(i).toString());
        }
        return Result.success(notificationsController.selectAllNotifications());
    }


    @DeleteMapping("/delete")
    public Result deleteNotifications(Integer id){
        notificationsController.deleteNotifications(id);
        return Result.success("删除成功");
    }

    @GetMapping("/updateIsRead")
    public Result updateIsRead(Integer id){
        System.out.println("id"+id);
        notificationsController.updateIsRead(id);
        return Result.success("更新成功");
    }


    @GetMapping("/isRead")
    public Result selectIsRead(){
        List<Notifications> notifications = notificationsController.selectIsRead();
        for (int i=0;i<notifications.size();i++){
            System.out.println("数据为"+notifications.get(i).toString());
        }
        return Result.success(notificationsController.selectIsRead());
    }


}
