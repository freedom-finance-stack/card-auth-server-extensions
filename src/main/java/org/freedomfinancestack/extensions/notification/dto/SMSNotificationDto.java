package org.freedomfinancestack.extensions.notification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SMSNotificationDto extends NotificationDto {
    int priority;
    String message;
    List<String> to;
}
