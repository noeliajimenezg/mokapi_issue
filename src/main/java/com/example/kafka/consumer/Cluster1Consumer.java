package com.example.kafka.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Cluster1Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Cluster1Consumer.class);

    @KafkaListener(
        topics = "${kafka.cluster1.topic}",
        containerFactory = "cluster1KafkaListenerContainerFactory"
    )
    public void listen(
        @Payload JsonNode message,
        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
        @Header(KafkaHeaders.OFFSET) long offset
    ) {
        logger.info("Cluster 1 Consumer - Received message from topic: {}, partition: {}, offset: {}", 
                   topic, partition, offset);
        logger.info("Cluster 1 Consumer - Message content: {}", message.toString());
        
        // Additional processing logic can be added here
        processMessage(message);
    }

    private void processMessage(JsonNode message) {
        // Process the JSON message as needed
        logger.debug("Cluster 1 Consumer - Processing message: {}", message.toPrettyString());
        
        // Example: Extract specific fields if needed
        if (message.has("messageId")) {
            logger.info("Cluster 1 Consumer - Message ID: {}", message.get("messageId").asText());
        }
        
        if (message.has("timestamp")) {
            logger.info("Cluster 1 Consumer - Message timestamp: {}", message.get("timestamp").asText());
        }
    }
}