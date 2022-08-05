package com.anodiam.notification.email.service;

import com.anodiam.core.NotificationRequest;

public interface EmailService {
    // To send a simple email
    void sendSimpleMail(NotificationRequest details);

    // To send an email with attachment
    void sendMailWithAttachment(NotificationRequest details);
}
