package com.zerobeta.contentpublication.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    private  final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics="ML-AL")
    public void consumeMlAl(@Payload String message) {
        logger.info("message :: {}", message);
        simpMessagingTemplate.convertAndSend("/topic/mlal", message);

    }

    @KafkaListener(topics="Big-Data")
    public void consumeBigData(@Payload String message) {
        simpMessagingTemplate.convertAndSend("/topic/bigdata", message);

    }

    @KafkaListener(topics="Micro-services")
    public void consumeMicroService(@Payload String message) {
        simpMessagingTemplate.convertAndSend("/topic/microservice", message);

    }
}
