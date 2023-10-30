package org.freedomfinancestack.extensions.hsm.exception;

import lombok.NonNull;

public class HSMValidationException extends HSMException {

    private static final long serialVersionUID = 1L;

    public HSMValidationException(
            @NonNull final HSMErrorCode hsmErrorCode,
            @NonNull final String message,
            @NonNull final Throwable throwable) {
        super(hsmErrorCode, message, throwable);
    }

    public HSMValidationException(
            @NonNull final HSMErrorCode hsmErrorCode, @NonNull final Throwable cause) {
        super(hsmErrorCode, cause);
    }

    public HSMValidationException(@NonNull final HSMErrorCode hsmErrorCode) {
        super(hsmErrorCode);
    }

    public HSMValidationException(
            @NonNull final HSMErrorCode hsmErrorCode, @NonNull final String message) {
        super(hsmErrorCode, message);
    }
}
