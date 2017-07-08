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
public class AppLogConfig extends BaseConfig {

    public final static String O2O_LOG_QUEUE = "o2o-log-queue";
    public final static String O2O_LOG_ROUTINGKEY = "o2o-log-route";

    @Bean
    public Queue logeQueue() {
        return new Queue(O2O_LOG_QUEUE, false);
    }

    @Bean(name = "logBinding")
    public Binding binding(Queue logeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(logeQueue).to(exchange).with(O2O_LOG_ROUTINGKEY);
    }

}
