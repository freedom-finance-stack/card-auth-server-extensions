package org.freedomfinancestack.extensions.validation.validator.enriched;

import java.util.List;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

public class NotIn implements Validator<String> {

    private final List<String> excludedValues;

    public NotIn(List<String> excludedValues) {
        this.excludedValues = excludedValues;
    }

    public static NotIn notIn(List<String> excludedValues) {
        return new NotIn(excludedValues);
    }

    @Override
    public void validate(String value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        if (excludedValues.contains(value)) {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT_VALUE, "IsInRule failed");
        }
    }
}
