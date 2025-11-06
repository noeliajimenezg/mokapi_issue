# Spring Boot Kafka Multi-Cluster Consumer Application

This workspace contains a Spring Boot application with Kafka consumers that connect to different clusters for development purposes.

## Project Structure
- **Main Application**: Spring Boot app with @KafkaListener annotations
- **Kafka Consumers**: Two consumers connecting to different Kafka clusters  
- **Message Format**: JSON messages that are logged upon receipt
- **Development Setup**: Mokapi for Kafka simulation using Docker containers

## Development Guidelines
- Use Maven for dependency management
- Follow Spring Boot best practices for Kafka integration
- Implement proper JSON message handling and logging
- Use Docker Compose for local development environment with Mokapi

## Key Components
- Spring Boot Kafka integration
- Multiple Kafka cluster configuration
- JSON message processing
- Docker-based development environment
- Mokapi configuration for Kafka simulation

## Setup Complete
✅ All project files created and configured
✅ Maven dependencies configured
✅ Spring Boot application structure in place
✅ Kafka consumers for multiple clusters implemented
✅ Mokapi configuration for development simulation
✅ Docker Compose setup complete
✅ VS Code tasks configured for easy development

## Quick Start
1. Run the "Start Docker Containers" task to start Mokapi
2. Run the "Spring Boot Run" task to start the application
3. Monitor logs to see JSON messages being consumed from both clusters

The application is ready for development!