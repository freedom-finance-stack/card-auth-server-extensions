package org.freedomfinancestack.extensions.validation.basic;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.Validatable;
import org.freedomfinancestack.extensions.validation.Validator;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;

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
