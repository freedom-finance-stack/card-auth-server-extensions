package org.freedomfinancestack.extensions.hsm.exception;

import lombok.Getter;
import lombok.NonNull;

public class HSMException extends Exception {

    private static final long serialVersionUID = 1L;

    @Getter private final HSMErrorCode hsmErrorCode;

    public HSMException(
            @NonNull final HSMErrorCode hsmErrorCode,
            @NonNull final String message,
            @NonNull final Throwable cause) {
        super(message, cause);
        this.hsmErrorCode = hsmErrorCode;
    }

    public HSMException(@NonNull final HSMErrorCode hsmErrorCode, @NonNull final Throwable cause) {
        super(hsmErrorCode.getDefaultErrorMessage(), cause);
        this.hsmErrorCode = hsmErrorCode;
    }

    public HSMException(@NonNull final HSMErrorCode hsmErrorCode) {
        super(hsmErrorCode.getDefaultErrorMessage());
        this.hsmErrorCode = hsmErrorCode;
    }

    public HSMException(@NonNull final HSMErrorCode hsmErrorCode, @NonNull final String message) {
        super(message);
        this.hsmErrorCode = hsmErrorCode;
    }
}
