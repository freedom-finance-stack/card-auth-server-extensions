package org.freedomfinancestack.extensions.notification.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SMSNotificationDto extends NotificationDto {
    int priority;
    String message;
    List<String> to;
}
