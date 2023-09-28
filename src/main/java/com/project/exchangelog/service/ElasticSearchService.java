package com.project.exchangelog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.exchangelog.log.Log;

@Service
public class ElasticSearchService {
    // Injecting the Elasticsearch URL from application.properties using @Value
    // annotation
    @Value("${elasticsearch.rest.uris}")
    private String elasticsearchUrl;

    private final RestTemplate restTemplate;

    public ElasticSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method for sending a log to Elasticsearch
    public void sendLogToElasticsearch(Log log) {
        // Constructing the Elasticsearch URL for the index and document type
        String elasticUrl = elasticsearchUrl + "/databaseExchanges/_doc";

        // Creating HttpHeaders for the HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Converting the log object to JSON format
        String logJson = "{\"operationDate\":\"" + log.getOperationDate() + "\",\"operationTitle\":\""
                + log.getOperationTitle() +
                "\",\"relatedTable\":\"" + log.getRelatedTable() + "\",\"operatedObject\":\"" + log.getOperatedObject()
                + "\"}";

        // Creating an HTTP request entity with the JSON log data and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(logJson, headers);

        // Sending the HTTP POST request to Elasticsearch to store the log
        restTemplate.postForEntity(elasticUrl, requestEntity, String.class);
    }
}
