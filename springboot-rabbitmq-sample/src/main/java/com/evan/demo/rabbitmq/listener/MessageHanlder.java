package com.evan.demo.rabbitmq.listener;

import com.evan.demo.rabbitmq.config.AppLogConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Evan on 2017-07-08.
 */
@Component
public class MessageHanlder {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int number = 100;

    @RabbitListener(queues = AppLogConfig.O2O_LOG_QUEUE)
    @RabbitHandler
    public void processLogMessage(Object message) {
        try {
            if (number <= 0) {
                logger.info("====== game over ======");
                return;
            }
            logger.info("====> receive message, number=" + number);
            number--;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
