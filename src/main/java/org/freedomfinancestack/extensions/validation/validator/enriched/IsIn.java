package org.freedomfinancestack.extensions.validation.validator.enriched;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

public class IsIn<T> implements Validator<T> {

    private final T[] acceptedValues;

    public IsIn(T[] acceptedValues) {
        this.acceptedValues = acceptedValues;
    }

    public static <T> IsIn<T> isIn(T[] acceptedValues) {
        return new IsIn<>(acceptedValues);
    }

    @Override
    public void validate(T value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }

        for (T acceptedValue : acceptedValues) {
            if (acceptedValue.equals(value)) {
                return;
            }
        }

        throw new ValidationException(ValidationErrorCode.INVALID_FORMAT_VALUE, "IsInRule failed");
    }
}
