package com.evan.demo.rabbitmq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;

/**
 * Created by Evan on 2017-07-08.
 */

public class BaseConfig {

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(AmqpConfig.O2O_EXCHANGE);
    }
}
