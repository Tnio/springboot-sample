package com.evan.demo.rabbitmq.controller;

import com.evan.demo.rabbitmq.config.AppLogConfig;
import com.evan.demo.rabbitmq.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
public class RabbitmqController {

    @Autowired
    private MessageSender sender;

    @GetMapping("/send")
    public String send(HttpServletRequest request, @RequestParam String msg) {
        Executor executor = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 1000; i++) {
            Runnable task = () -> {
                sender.senMessage(msg, AppLogConfig.O2O_LOG_ROUTINGKEY);
            };
            executor.execute(task);
        }
        return "Send OK.";
    }


}  