package org.freedomfinancestack.extensions.validation.validator;

import org.freedomfinancestack.extensions.validation.exception.ValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validation {
    @SafeVarargs
    public static <T> void validate(String fieldName, T value, Validator<T>... validationRules)
            throws ValidationException {
        for (Validator<T> validationRule : validationRules) {
            try {
                validationRule.validate(value);
            } catch (ValidationException e) {
                log.error(
                        "Validation failed for rule: {}, field: {}",
                        validationRule.getClass().getSimpleName(),
                        fieldName);
                throw new ValidationException(
                        e.getValidationErrorCode(), fieldName, "Invalid value for " + fieldName);
            }
        }
    }
}
