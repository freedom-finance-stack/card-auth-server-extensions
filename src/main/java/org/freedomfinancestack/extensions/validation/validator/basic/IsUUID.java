package org.freedomfinancestack.extensions.validation.validator.basic;

import java.util.UUID;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsUUID implements Validator<String> {
    private static final IsUUID INSTANCE = new IsUUID();

    public static IsUUID isUUID() {
        return INSTANCE;
    }

    @Override
    public void validate(String value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException exception) {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT_VALUE, "Invalid value");
        }
    }
}
