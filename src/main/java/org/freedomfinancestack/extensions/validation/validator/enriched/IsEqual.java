package org.freedomfinancestack.extensions.validation.validator.enriched;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

public class IsEqual<T> implements Validator<T> {

    private final T acceptedValue;

    public IsEqual(T acceptedValue) {
        this.acceptedValue = acceptedValue;
    }

    public static <T> IsEqual<T> isEqual(T acceptedValue) {
        return new IsEqual<>(acceptedValue);
    }

    @Override
    public void validate(T value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }

        if (acceptedValue.equals(value)) {
            return;
        }

        throw new ValidationException(ValidationErrorCode.NOT_EQUAL, "IsEqual failed");
    }
}
