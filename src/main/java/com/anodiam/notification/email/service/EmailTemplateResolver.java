package com.anodiam.notification.email.service;

import com.anodiam.core.NotificationRequest;

public class EmailTemplateResolver {
    public static String getTemplate(final NotificationRequest.NotificationType notificationType) {
        if(notificationType.equals(NotificationRequest.NotificationType.STUDENT_SIGNUP)) {
            return "student-signup";
        } else if(notificationType.equals(NotificationRequest.NotificationType.TEACHER_SIGNUP)) {
            return "teacher-signup";
        } else if(notificationType.equals(NotificationRequest.NotificationType.ADMIN_SIGNUP)) {
            return "admin-signup";
        } else {
            throw new RuntimeException("Invalid notification type");
        }
    }
}
