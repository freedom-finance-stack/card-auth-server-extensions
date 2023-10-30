package org.freedomfinancestack.extensions.validation.validator.enriched;

import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

public class MinMaxValidator<T> implements Validator<T> {
    private final int min;
    private final int max;

    public MinMaxValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static <T> MinMaxValidator<T> minMaxValidator(int min, int max) {
        return new MinMaxValidator<T>(min, max);
    }

    @Override
    public void validate(T value) throws ValidationException {
        if (value == null) {
            return;
        }

        if (value instanceof String) {
            String actualValue = (String) value;
            if ((min > 0 && actualValue.length() < min)
                    || (max > 0 && actualValue.length() > max)
                    || (min == 0 && max == 0 && actualValue.length() > 0)) {
                throw new ValidationException(
                        ValidationErrorCode.INVALID_FORMAT_LENGTH, String.format("Invalid value "));
            }
        } else if (value instanceof Integer) {
            int actualValue = (int) value;
            if ((min > 0 && actualValue < min)
                    || (max > 0 && actualValue > max)
                    || (min == 0 && max == 0 && actualValue > 0)) {
                throw new ValidationException(
                        ValidationErrorCode.INVALID_FORMAT_LENGTH, String.format("Invalid value "));
            }
        } else {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT, String.format("Invalid value "));
        }
    }
}
