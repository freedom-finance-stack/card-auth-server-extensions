package org.freedomfinancestack.extensions.scheduledTask.exception;

import lombok.NonNull;

/**
 * An exception thrown when a task with the same ID already exists.
 *
 * @author jaydeepRadadiya
 * @since 1.0.4
 * @version 1.0.4
 */
public class TaskAlreadyExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public TaskAlreadyExistException(@NonNull String message) {
        super(message);
    }
}
