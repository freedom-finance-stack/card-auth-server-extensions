package org.freedomfinancestack.extensions.validation.validator.basic;

import java.util.stream.Stream;

import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsNumericTest {

    @ParameterizedTest
    @MethodSource("provideValidateValidIsNumeric")
    public void testValidateValidIsNumeric(String value) throws ValidationException {
        IsNumeric.isNumeric().validate(value);
        // No exception should be thrown
    }

    public static Stream<String> provideValidateValidIsNumeric() {
        return Stream.of("123", "0", "1", "", null);
    }

    @Test
    public void testValidateInvalidIsNumeric() throws ValidationException {
        String value = "abc123";
        try {
            IsNumeric.isNumeric().validate(value);
        } catch (ValidationException e) {
            assertEquals(ValidationErrorCode.INVALID_FORMAT_VALUE, e.getValidationErrorCode());
        }
    }
}
