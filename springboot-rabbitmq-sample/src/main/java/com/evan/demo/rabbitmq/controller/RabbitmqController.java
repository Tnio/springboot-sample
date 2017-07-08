package com.evan.demo.rabbitmq.controller;

import com.evan.demo.rabbitmq.config.AppLogConfig;
import com.evan.demo.rabbitmq.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RabbitmqController {

    @Autowired
    private MessageSender sender;

    @GetMapping("/send")
    public String send(HttpServletRequest request, @RequestParam String msg) {
        sender.senMessage(msg, AppLogConfig.O2O_LOG_ROUTINGKEY);
        return "Send OK.";
    }


}  