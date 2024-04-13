package com.bistu.ckkj.controller;

import com.bistu.ckkj.aop.MyLog;
import com.bistu.ckkj.pojo.Notifications;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationsService notificationsController;

    @MyLog(value = "")
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
        for (Notifications notification : notifications) {
            System.out.println("数据为" + notification.toString());
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
        for (Notifications notification : notifications) {
            System.out.println("数据为" + notification.toString());
        }
        return Result.success(notificationsController.selectIsRead());
    }


}
