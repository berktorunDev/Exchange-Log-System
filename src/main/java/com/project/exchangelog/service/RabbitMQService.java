package com.project.exchangelog.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.project.exchangelog.log.Log;

@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Send a log message to the "logQueue" in RabbitMQ
    public void sendLogToQueue(Log log) {
        rabbitTemplate.convertAndSend("logQueue", log);
    }
}