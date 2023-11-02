package org.freedomfinancestack.extensions.validation.validator.enriched;

import java.util.List;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.enums.DataLengthType;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

public class isListLengthValid<T> implements Validator<List<T>> {

    private final int length;
    private final DataLengthType lengthType;

    public isListLengthValid(int length, DataLengthType lengthType) {
        this.length = length;
        this.lengthType = lengthType;
    }

    public static <T> isListLengthValid<T> isListLengthValid(
            DataLengthType lengthType, int length) {
        return new isListLengthValid<T>(length, lengthType);
    }

    @Override
    public void validate(List<T> values) throws ValidationException {
        if (Util.isNullorBlank(values)) {
            return;
        }
        if (DataLengthType.FIXED.equals(lengthType)) {
            if (length != values.size()) {
                throw new ValidationException(
                        ValidationErrorCode.INVALID_FORMAT_LENGTH, "Invalid value");
            }
        } else if (DataLengthType.VARIABLE.equals(lengthType)) {
            if (values.size() > length) {
                throw new ValidationException(
                        ValidationErrorCode.INVALID_FORMAT_LENGTH, "Invalid value");
            }
        }
    }
}
