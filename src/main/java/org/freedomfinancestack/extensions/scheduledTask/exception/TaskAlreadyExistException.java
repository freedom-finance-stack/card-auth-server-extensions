package org.freedomfinancestack.extensions.scheduledTask.exception;

import lombok.NonNull;

public class TaskAlreadyExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public TaskAlreadyExistException(@NonNull String message) {
        super(message);
    }
}
