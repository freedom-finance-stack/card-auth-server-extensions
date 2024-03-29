package org.freedomfinancestack.extensions.notification.dto;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailNotificationDto extends NotificationDto {
    String templateName;
    Map<String, String> templateData;
    String from;
    List<String> to;
    String subject;
    String cc;
}
