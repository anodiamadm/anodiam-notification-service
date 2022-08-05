package com.anodiam.core;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationRequest {
    protected String recipientEmail;
    protected String recipientFirstName;
    protected String recipientLastName;
    protected JwtToken validationToken;
    protected String subject;
    protected String attachment;
    protected NotificationType notificationType;

    public static enum NotificationType {
        STUDENT_SIGNUP,
        TEACHER_SIGNUP,
        ADMIN_SIGNUP;
    }
}
