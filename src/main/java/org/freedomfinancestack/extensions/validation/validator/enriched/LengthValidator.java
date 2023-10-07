package org.freedomfinancestack.extensions.validation.validator.enriched;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;
import org.freedomfinancestack.razorpay.cas.admin.utils.Util;
import org.freedomfinancestack.razorpay.cas.admin.validation.validator.Validator;

public class LengthValidator implements Validator<String> {

    private final int length;
    private final DataLengthType lengthType;

    public LengthValidator(DataLengthType lengthType, int length) {
        this.length = length;
        this.lengthType = lengthType;
    }

    public static LengthValidator lengthValidator(DataLengthType lengthType, int length) {
        return new LengthValidator(lengthType, length);
    }

    @Override
    public void validate(String value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        if (DataLengthType.FIXED.equals(lengthType)) {
            if (length != value.length()) {
                throw new ValidationException(
                        ValidationErrorCode.INVALID_FORMAT_LENGTH, "Invalid value ");
            }
        } else if (DataLengthType.VARIABLE.equals(lengthType)) {
            if (value.length() > length) {
                throw new ValidationException(
                        ValidationErrorCode.INVALID_FORMAT_LENGTH, "Invalid value ");
            }
        }
    }

    public enum DataLengthType {
        FIXED,
        VARIABLE
    }
}
