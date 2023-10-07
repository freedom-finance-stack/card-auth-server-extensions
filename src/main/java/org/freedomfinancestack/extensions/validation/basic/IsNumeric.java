package org.freedomfinancestack.extensions.validation.basic;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.Validator;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsNumeric implements Validator<String> {
    private static final IsNumeric INSTANCE = new IsNumeric();

    public static IsNumeric isNumeric() {
        return INSTANCE;
    }

    @Override
    public void validate(String value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        if (!value.matches("[0-9]+")) {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT_VALUE, "Invalid value");
        }
    }
}
