package org.freedomfinancestack.extensions.notification.exception;

import java.io.Serial;

import lombok.Getter;
import lombok.NonNull;

public class NotificationException extends Exception {

    @Serial private static final long serialVersionUID = 1L;

    @Getter private final NotificationErrorCode errorCode;

    public NotificationException(
            @NonNull final NotificationErrorCode errorCode,
            @NonNull final String message,
            @NonNull final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public NotificationException(
            @NonNull final NotificationErrorCode errorCode, @NonNull final Throwable cause) {
        super(errorCode.getDefaultErrorMessage(), cause);
        this.errorCode = errorCode;
    }

    public NotificationException(@NonNull final NotificationErrorCode errorCode) {
        super(errorCode.getDefaultErrorMessage());
        this.errorCode = errorCode;
    }

    public NotificationException(
            @NonNull final NotificationErrorCode errorCode, @NonNull final String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
