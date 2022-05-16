package com.example.demo.inbound.messaging;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Config {
    private final AmqpTemplate amqpTemplate;
    public void publish(Object payload, String exchange, String routingKey){
      amqpTemplate.convertAndSend(exchange,routingKey,payload);
    }
}
