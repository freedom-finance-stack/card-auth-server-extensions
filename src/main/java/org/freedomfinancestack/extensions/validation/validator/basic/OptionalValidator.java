package org.freedomfinancestack.extensions.validation.validator.basic;

import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionalValidator<T> implements Validator<T> {

    public static <T> OptionalValidator<T> optionalValidator() {
        return new OptionalValidator<T>();
    }

    @Override
    public void validate(T value) throws ValidationException {
        if (value != null && "".equals(value.toString())) {
            throw new ValidationException(ValidationErrorCode.INVALID_FORMAT, "Invalid value");
        }
    }
}
