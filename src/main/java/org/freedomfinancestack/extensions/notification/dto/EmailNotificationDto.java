package org.freedomfinancestack.extensions.notification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EmailNotificationDto extends NotificationDto {
    String templateName;
    Map<String, String> templateData;
    String from;
    List<String> to;
    String subject;
    String cc;
}
