package com.evan.demo.rabbitmq.listener;

import com.evan.demo.rabbitmq.config.AppLogConfig;
import com.evan.demo.rabbitmq.config.AppOrderConfig;
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

    @RabbitListener(queues = AppLogConfig.O2O_LOG_QUEUE)
    @RabbitHandler
    public void processLogMessage(Object message) {
        try {
            if (message instanceof Message) {
                String messageBody = new String(((Message) message).getBody(), "utf-8");
                ObjectMapper objectMapper = new ObjectMapper();
                logger.info("====> receive log message: " + objectMapper.writeValueAsString(messageBody));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = AppOrderConfig.O2O_ORDER_QUEUE)
    @RabbitHandler
    public void processOrderMessage(Object message) {
        try {
            if (message instanceof Message) {
                String messageBody = new String(((Message) message).getBody(), "utf-8");
                ObjectMapper objectMapper = new ObjectMapper();
                logger.info("====> receive order message: " + objectMapper.writeValueAsString(messageBody));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
