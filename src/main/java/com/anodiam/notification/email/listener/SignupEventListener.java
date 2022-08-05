package com.anodiam.notification.email.listener;

import com.anodiam.core.NotificationRequest;
import com.anodiam.notification.email.model.SignupNotificationRequest;
import com.anodiam.notification.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class SignupEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignupEventListener.class);

    @Value("${LOGIN_SERVICE_BASE_URL}")
    private String baseUrl;
    private final EmailService emailService;

    public SignupEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${anodiam.login.notification.topic.name}", groupId = "anodiam-login-notification-cg")
    public void onMessage(@Payload final NotificationRequest notificationRequest) {
        LOGGER.info("Sending email");
        final SignupNotificationRequest signupNotificationRequest = new SignupNotificationRequest();
        signupNotificationRequest.setRecipientEmail(notificationRequest.getRecipientEmail());
        signupNotificationRequest.setRecipientFirstName(notificationRequest.getRecipientFirstName());
        signupNotificationRequest.setRecipientLastName(notificationRequest.getRecipientLastName());
        signupNotificationRequest.setValidationToken(notificationRequest.getValidationToken());
        signupNotificationRequest.setSubject(notificationRequest.getSubject());
        signupNotificationRequest.setAttachment(notificationRequest.getAttachment());
        signupNotificationRequest.setNotificationType(notificationRequest.getNotificationType());
        signupNotificationRequest.setBaseUrl(baseUrl);
        emailService.sendSimpleMail(signupNotificationRequest);
        LOGGER.info("Email sent");
    }
}
