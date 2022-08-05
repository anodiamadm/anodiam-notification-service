package com.anodiam.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AnodiamNotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnodiamNotificationApplication.class, args);
    }
}
