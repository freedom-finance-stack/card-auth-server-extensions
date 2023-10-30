package org.freedomfinancestack.extensions.notification.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSNotificationDto extends NotificationDto {
    int priority;
    String message;
    List<String> to;
}
