package com.evan.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext app = SpringApplication.run(Application.class, args);

        while (true) {
            Sender sender = app.getBean(Sender.class);
            sender.sendMessage();
            Thread.sleep(5000);
        }
    }
}
