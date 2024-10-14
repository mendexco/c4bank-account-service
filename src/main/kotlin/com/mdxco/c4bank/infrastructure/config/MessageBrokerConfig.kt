package com.mdxco.c4bank.infrastructure.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessageBrokerConfig {
    @Bean
    fun addressQueue(): Queue {
        return Queue("addressQueue", false)
    }

    @Bean
    fun addressExchange(): TopicExchange {
        return TopicExchange("addressExchange")
    }

    @Bean
    fun addressBinding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("address.routing.key")
    }
}