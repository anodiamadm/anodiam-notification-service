package com.anodiam.notification.email.model;

import com.anodiam.core.NotificationRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignupNotificationRequest extends NotificationRequest {
    private String baseUrl;
}
