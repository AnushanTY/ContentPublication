package com.zerobeta.contentpublication.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics="ML-AL")
    public void consumeMlAl(@Payload String message) {
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
