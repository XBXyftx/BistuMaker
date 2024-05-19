package com.bistu.ckkj.controller;

import com.bistu.ckkj.pojo.Messages;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Result addMessages(@RequestBody Messages message) {
        messagesService.insert(message);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteMessages(Integer id) {
        messagesService.delete(id);
        return Result.success();
    }





}
