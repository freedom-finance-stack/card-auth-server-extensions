package org.freedomfinancestack.extensions.validation.validator;

import org.freedomfinancestack.extensions.validation.exception.ValidationException;

public interface Validator<T> {
    void validate(T value) throws ValidationException;

    default Validator<T> and(Validator<T> other) {
        return value -> {
            validate(value);
            other.validate(value);
        };
    }
}
