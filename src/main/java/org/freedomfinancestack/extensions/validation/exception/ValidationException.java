package org.freedomfinancestack.extensions.validation.exception;

import lombok.Getter;
import lombok.NonNull;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    @Getter private final ValidationErrorCode validationErrorCode;

    public ValidationException(
            @NonNull final ValidationErrorCode validationErrorCode, @NonNull final String message) {
        super(message);
        this.validationErrorCode = validationErrorCode;
    }

    public ValidationException(
            @NonNull final ValidationErrorCode validationErrorCode,
            @NonNull final String message,
            @NonNull final Throwable cause) {
        super(message, cause);
        this.validationErrorCode = validationErrorCode;
    }

    public ValidationException(
            @NonNull final ValidationErrorCode validationErrorCode,
            @NonNull final Throwable cause) {
        super(validationErrorCode.getDefaultErrorMessage(), cause);
        this.validationErrorCode = validationErrorCode;
    }

    public ValidationException(@NonNull final ValidationErrorCode validationErrorCode) {
        super(validationErrorCode.getDefaultErrorMessage());
        this.validationErrorCode = validationErrorCode;
    }
}
