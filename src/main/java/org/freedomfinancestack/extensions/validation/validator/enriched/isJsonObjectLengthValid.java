package org.freedomfinancestack.extensions.validation.validator.enriched;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class isJsonObjectLengthValid implements Validator<Object> {

    private final int length;
    public static Gson gson = new GsonBuilder().create();

    public isJsonObjectLengthValid(int length) {
        this.length = length;
    }

    public static isJsonObjectLengthValid isJsonObjectLengthValid(int length) {
        return new isJsonObjectLengthValid(length);
    }

    @Override
    public void validate(Object value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        String dataString;
        try {
            dataString = gson.toJson(value);
        } catch (Exception e) {
            throw new ValidationException(ValidationErrorCode.INVALID_FORMAT, "Invalid value");
        }

        if (!dataString.isEmpty() && dataString.length() > length) {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT_LENGTH, "Invalid value");
        }
    }
}
