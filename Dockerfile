FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy JAR
COPY target/activity-service-0.1.0.jar ./activity-service.jar

# Copy configuration
COPY src/main/resources/config.yaml ./config.yaml

# Expose port as defined in config.yaml
EXPOSE 8085

# Run JAR with explicit config
CMD ["java", "-jar", "activity-service.jar"]
