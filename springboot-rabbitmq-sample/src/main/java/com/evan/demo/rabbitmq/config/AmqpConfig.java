package com.evan.demo.rabbitmq.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Evan on 2017-07-07.
 */
@Configuration
public class AmqpConfig {

    public static final String FOO_EXCHANGE = "sample.exchange.foo";
    public static final String FOO_ROUTINGKEY = "sample.routingkey.foo";
    public static final String FOO_QUEUE = "sample.queue.foo";


}
