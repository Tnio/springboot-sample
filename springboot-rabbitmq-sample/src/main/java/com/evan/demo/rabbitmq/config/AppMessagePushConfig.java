package com.evan.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Evan on 2017-07-08.
 */
@Configuration
public class AppMessagePushConfig extends BaseConfig {

    public final static String O2O_MESSAGE_PUSH_QUEUE = "o2o-message-push-queue";
    public final static String O2O_MESSAGE_PUSH_ROUTINGKEY = "o2o-message-push-route";

    @Bean
    public Queue messagePushQuene() {
        return new Queue(O2O_MESSAGE_PUSH_QUEUE, false);
    }

    @Bean(name = "messagePushBinding")
    public Binding binding(Queue messagePushQuene, DirectExchange exchange) {
        return BindingBuilder.bind(messagePushQuene).to(exchange).with(O2O_MESSAGE_PUSH_ROUTINGKEY);
    }

}
