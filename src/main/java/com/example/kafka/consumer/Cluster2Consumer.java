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
public class Cluster2Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Cluster2Consumer.class);

    @KafkaListener(
        topics = "${kafka.cluster2.topic}",
        containerFactory = "cluster2KafkaListenerContainerFactory"
    )
    public void listen(
        @Payload JsonNode message,
        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
        @Header(KafkaHeaders.OFFSET) long offset
    ) {
        logger.info("Cluster 2 Consumer - Received message from topic: {}, partition: {}, offset: {}", 
                   topic, partition, offset);
        logger.info("Cluster 2 Consumer - Message content: {}", message.toString());
        
        // Additional processing logic can be added here
        processMessage(message);
    }

    private void processMessage(JsonNode message) {
        // Process the JSON message as needed
        logger.debug("Cluster 2 Consumer - Processing message: {}", message.toPrettyString());
        
        // Example: Extract specific fields if needed
        if (message.has("eventType")) {
            logger.info("Cluster 2 Consumer - Event type: {}", message.get("eventType").asText());
        }
        
        if (message.has("data")) {
            logger.info("Cluster 2 Consumer - Data payload: {}", message.get("data").toString());
        }
    }
}