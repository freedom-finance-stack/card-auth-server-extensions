package org.freedomfinancestack.extensions.validation.validator.basic;

import java.util.stream.Stream;

import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotEmptyTest {

    @ParameterizedTest
    @MethodSource("provideValidateValidNotEmpty")
    public void testValidateValidNotEmpty(Object val) throws ValidationException {
        NotEmpty.notEmpty().validate(val);
        // No exception should be thrown
    }

    public static Stream<Object> provideValidateValidNotEmpty() {
        return Stream.of("123", new Object(), 12);
    }

    public static Stream<Object> provideValidateInvalidNotEmpty() {
        return Stream.of("");
    }

    @ParameterizedTest
    @MethodSource("provideValidateInvalidNotEmpty")
    public void testValidateInValidNotEmpty(Object val) {
        ValidationException e =
                assertThrows(ValidationException.class, () -> NotEmpty.notEmpty().validate(val));
        assertEquals(ValidationErrorCode.INVALID_FORMAT_VALUE, e.getValidationErrorCode());
    }
}
