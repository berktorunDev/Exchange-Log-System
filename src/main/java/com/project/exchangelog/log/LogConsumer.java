package com.project.exchangelog.log;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.project.exchangelog.service.ElasticSearchService;

@Component
public class LogConsumer {
    // This component listens to the "logQueue" RabbitMQ queue and consumes log
    // messages.

    private final ElasticSearchService elasticSearchService;

    public LogConsumer(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @RabbitListener(queues = "logQueue")
    public void receiveLogMessage(Log log) {
        // When a log message is received, it is passed to the ElasticSearchService for
        // processing.
        elasticSearchService.sendLogToElasticsearch(log);
    }
}
