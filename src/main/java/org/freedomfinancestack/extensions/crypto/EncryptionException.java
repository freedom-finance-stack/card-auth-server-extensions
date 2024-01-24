package org.freedomfinancestack.extensions.crypto;

import lombok.NonNull;

/** Exception thrown for encryption-related errors. */
public class EncryptionException extends RuntimeException {

    /**
     * Constructs an EncryptionException with the specified message and cause.
     *
     * @param message The detail message.
     * @param cause The cause of the exception.
     */
    public EncryptionException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an EncryptionException with the specified message.
     *
     * @param message The detail message.
     */
    public EncryptionException(@NonNull final String message) {
        super(message);
    }
}
