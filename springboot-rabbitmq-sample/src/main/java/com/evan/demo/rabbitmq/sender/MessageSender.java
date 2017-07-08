package com.evan.demo.rabbitmq.sender;

import com.evan.demo.rabbitmq.config.AmqpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Evan on 2017-07-07.
 */
@Service
public class MessageSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param str
     * @param routingKey
     */
    public void senMessage(String str, String routingKey) {
        try {
            logger.info("====> send message: " + str);
            rabbitTemplate.convertAndSend(AmqpConfig.O2O_EXCHANGE, routingKey, str);
        } catch (Exception ex) {
            logger.error("====> MessageSender.senMessage: " + ex.getMessage(), ex);
        }
    }

    /**
     * 回调方法
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("====> confirm: " + correlationData.getId());
    }

}
