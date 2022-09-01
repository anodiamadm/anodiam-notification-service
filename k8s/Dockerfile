# For Java 11
FROM adoptopenjdk/openjdk11:alpine-jre

# Copy jar
COPY anodiam-notification-service-*.jar /anodiam-notification-service.jar

# Run
ENTRYPOINT ["java", "-jar","/anodiam-notification-service.jar"]