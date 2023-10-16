package org.freedomfinancestack.extensions.validation.validator.basic;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validatable;
import org.freedomfinancestack.extensions.validation.validator.Validator;

public class IsValidObject<T extends Validatable> implements Validator<T> {

    public static <T extends Validatable> IsValidObject<T> isValidObject() {
        return new IsValidObject<T>();
    }

    @Override
    public void validate(Validatable value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        if (!value.isValid()) {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT_VALUE, "Invalid value");
        }
    }
}
