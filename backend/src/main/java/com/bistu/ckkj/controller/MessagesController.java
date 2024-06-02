package com.bistu.ckkj.controller;

import com.bistu.ckkj.pojo.Messages;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.MessagesService;
import com.bistu.ckkj.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessagesController {

    private final MessagesService messagesService;

    @GetMapping("/allInfo")
    public Result selectAllMessages() {
        return Result.success(messagesService.selectAll());
    }

    @PostMapping("/add")
    public Result addMessages(@RequestBody Messages message, HttpServletRequest request) {



        message.setIp(IpUtils.getIpAddr(request));

        messagesService.insert(message);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteMessages(Integer id) {
        messagesService.delete(id);
        return Result.success();
    }





}
