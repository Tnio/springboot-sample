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
public class AppScheduleTaskConfig extends BaseConfig {

    public final static String O2O_SCHEDULETASK_QUEUE = "o2o-scheduleTask-queue";
    public final static String O2O_SCHEDULETASK_ROUTINGKEY = "o2o-scheduleTask-route";

    @Bean(name = "scheduleTaskQueue")
    public Queue quene() {
        return new Queue(O2O_SCHEDULETASK_QUEUE, false);
    }

    @Bean(name = "scheduleTaskBinding")
    public Binding binding(Queue scheduleTaskQueue, DirectExchange exchange) {
        return BindingBuilder.bind(scheduleTaskQueue).to(exchange).with(O2O_SCHEDULETASK_ROUTINGKEY);
    }

}
