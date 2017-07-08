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
public class AppOrderConfig extends BaseConfig{

    public final static String O2O_ORDER_QUEUE = "o2o-order-queue";
    public final static String O2O_ORDER_ROUTINGKEY = "o2o-order-route";

    @Bean(name = "orderQueue")
    public Queue quene() {
        return new Queue(O2O_ORDER_QUEUE, false);
    }

    @Bean(name = "orderBinding")
    public Binding binding(Queue orderQueue, DirectExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with(O2O_ORDER_ROUTINGKEY);
    }
}
