package org.freedomfinancestack.extensions.notification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponseDto {
    boolean success;

    public static NotificationResponseDto success() {
        NotificationResponseDto dto = new NotificationResponseDto();
        dto.setSuccess(true);
        return dto;
    }

    public static NotificationResponseDto failure() {
        NotificationResponseDto dto = new NotificationResponseDto();
        dto.setSuccess(false);
        return dto;
    }
}
