package com.evan.demo.rabbitmq.controller;

import com.evan.demo.rabbitmq.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RabbitmqController {

    @Autowired
    private MessageSender sender;

    @GetMapping("/send")
    public String send(HttpServletRequest request, String msg) {
        sender.send(msg);
        return "Send OK.";
    }
}  