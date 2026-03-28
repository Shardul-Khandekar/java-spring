package com.learning.pilot.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.pilot.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);
    private static final String TOPIC = "orders.created";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate,
                         ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrder(Order order) {
        try {
            String payload = objectMapper.writeValueAsString(order);
            kafkaTemplate.send(TOPIC, order.getCustomerId(), payload);
            log.info("Produced → topic: {}, key: {}, payload: {}",
                    TOPIC, order.getCustomerId(), payload);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize order: {}", order, e);
        }
    }
}
