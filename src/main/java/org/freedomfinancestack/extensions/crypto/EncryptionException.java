package org.freedomfinancestack.extensions.crypto;

import lombok.NonNull;

public class EncryptionException extends RuntimeException {
    public EncryptionException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }

    public EncryptionException(@NonNull final String message) {
        super(message);
    }
}
