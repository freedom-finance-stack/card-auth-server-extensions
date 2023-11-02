package org.freedomfinancestack.extensions.notification.exception;

import lombok.Getter;

@Getter
public enum NotificationErrorCode {
    INVALID_CHANNEL_PROVIDED("Notification channel is invalid"),
    INVALID_NOTIFICATION_DTO("Notification DTO is invalid"),
    ;

    private final String defaultErrorMessage;

    NotificationErrorCode(String defaultErrorMessage) {
        this.defaultErrorMessage = defaultErrorMessage;
    }
}
