package com.evan.demo.rabbitmq.listener;

import com.evan.demo.rabbitmq.config.AmqpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Evan on 2017-07-07.
 */
@Configuration
@RabbitListener(queues = AmqpConfig.FOO_QUEUE)
public class MessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(AmqpConfig.FOO_EXCHANGE);
    }

    @Bean
    public Queue fooQueue() {
        return new Queue(AmqpConfig.FOO_QUEUE);
    }

    /**
     * 将队列绑定到交换机
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(fooQueue()).to(defaultExchange()).with(AmqpConfig.FOO_ROUTINGKEY);
    }

    @RabbitHandler
    public void process(Message message) {
        logger.info("====> Listener: " + message);
    }

}
