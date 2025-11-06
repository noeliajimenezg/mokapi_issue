# Kafka Multi-Cluster Consumer Application

A Spring Boot application that demonstrates consuming JSON messages from multiple Kafka clusters using `@KafkaListener` annotations. This project uses Mokapi for simulating Kafka clusters in development.

## Project Structure

```
├── src/main/java/com/example/kafka/
│   ├── KafkaMultiClusterConsumerApplication.java  # Main Spring Boot application
│   ├── config/
│   │   └── KafkaConfig.java                       # Kafka configuration for multiple clusters
│   └── consumer/
│       ├── Cluster1Consumer.java                  # Consumer for Kafka Cluster 1
│       └── Cluster2Consumer.java                  # Consumer for Kafka Cluster 2
├── src/main/resources/
│   └── application.properties                     # Application configuration
├── mokapi-config/                                 # Mokapi configuration files
│   ├── kafka-clusters.yml                        # Kafka cluster simulation config
│   └── mokapi.yml                                 # Main Mokapi configuration
├── docker-compose.yml                            # Docker setup with Mokapi
└── pom.xml                                       # Maven dependencies
```

## Features

- **Multi-Cluster Support**: Connects to two different Kafka clusters simultaneously
- **JSON Message Processing**: Deserializes and logs JSON messages
- **Flexible Configuration**: Easy configuration through application properties
- **Development Simulation**: Uses Mokapi for local Kafka cluster simulation
- **Comprehensive Logging**: Detailed logging of message consumption

## Configuration

The application is configured to connect to two Kafka clusters:

- **Cluster 1**: `localhost:9092` - Topic: `cluster1-topic`
- **Cluster 2**: `localhost:9094` - Topic: `cluster2-topic`

### Application Properties

```properties
# Kafka Cluster 1
kafka.cluster1.bootstrap-servers=localhost:9092
kafka.cluster1.group-id=cluster1-consumer-group
kafka.cluster1.topic=cluster1-topic

# Kafka Cluster 2
kafka.cluster2.bootstrap-servers=localhost:9094
kafka.cluster2.group-id=cluster2-consumer-group
kafka.cluster2.topic=cluster2-topic
```

## Running the Application

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker and Docker Compose

### Steps

1. **Start Mokapi and Kafka Simulation**:
   ```bash
   docker-compose up -d
   ```

2. **Build the Application**:
   ```bash
   mvn clean compile
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Monitor Logs**:
   The application will start consuming messages from both clusters and log them to the console.

### Stopping the Application

```bash
# Stop the Spring Boot application (Ctrl+C)
# Stop Docker containers
docker-compose down
```

## Message Format

The consumers expect JSON messages. Mokapi will automatically generate sample messages:

### Cluster 1 Messages (User Events):
```json
{
  "messageId": "msg-001",
  "timestamp": "2025-11-06T10:00:00Z",
  "userId": "user-123",
  "action": "login",
  "metadata": {
    "ip": "192.168.1.1",
    "userAgent": "Mozilla/5.0"
  }
}
```

### Cluster 2 Messages (Order Events):
```json
{
  "eventType": "order.created",
  "orderId": "order-456",
  "customerId": "customer-789",
  "data": {
    "amount": 99.99,
    "currency": "USD",
    "items": [
      {
        "productId": "prod-001",
        "quantity": 2,
        "price": 49.99
      }
    ]
  },
  "timestamp": "2025-11-06T10:05:00Z"
}
```

## Mokapi Configuration

Mokapi simulates two Kafka clusters and automatically generates sample messages:

- **Cluster 1**: Generates user events every 10 seconds
- **Cluster 2**: Generates order events every 15 seconds

Access the Mokapi admin interface at: http://localhost:8080

## Development

### Adding New Consumers

1. Create a new consumer class in `src/main/java/com/example/kafka/consumer/`
2. Use `@KafkaListener` annotation with appropriate `containerFactory`
3. Add configuration for the new cluster in `KafkaConfig.java`
4. Update `application.properties` with new cluster settings

### Customizing Message Processing

Each consumer has a `processMessage()` method where you can add custom logic for processing JSON messages.

## Troubleshooting

- **Connection Issues**: Ensure Docker containers are running with `docker-compose ps`
- **Port Conflicts**: Check if ports 8080, 9092, or 9094 are already in use
- **Message Format**: Verify JSON message structure matches expected schema
- **Logs**: Check application logs for detailed error information

## Dependencies

- Spring Boot 3.2.0
- Spring Kafka
- Jackson (JSON processing)
- Mokapi (development simulation)