package org.freedomfinancestack.extensions.validation.validator.enriched;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.freedomfinancestack.extensions.validation.validator.Validator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonObjectLengthValidator implements Validator<Object> {

    private final int length;
    public static Gson gson = new GsonBuilder().create();

    public JsonObjectLengthValidator(int length) {
        this.length = length;
    }

    public static JsonObjectLengthValidator jsonObjectLengthValidator(int length) {
        return new JsonObjectLengthValidator(length);
    }

    @Override
    public void validate(Object value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        String dataString = gson.toJson(value);
        if (dataString.length() > length) {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT_LENGTH, "Invalid value");
        }
    }
}
