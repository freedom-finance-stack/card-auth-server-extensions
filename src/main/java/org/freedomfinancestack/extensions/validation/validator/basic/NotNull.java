package org.freedomfinancestack.extensions.validation.validator.basic;

import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validatable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotNull<T> implements Validatable<T> {

    public static <T> NotNull<T> notNull() {
        return new NotNull<T>();
    }

    @Override
    public void validate(T value) throws ValidationException {
        if (null == value) {
            throw new ValidationException(
                    ValidationErrorCode.REQUIRED_DATA_ELEMENT_MISSING, "Invalid value");
        } else if ("".equals(value.toString())) {
            throw new ValidationException(
                    ValidationErrorCode.REQUIRED_DATA_ELEMENT_MISSING, "Invalid value");
        }
    }
}
