package org.freedomfinancestack.extensions.validation.validator.basic;

import java.util.stream.Stream;

import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotBlankTest {

    @ParameterizedTest
    @MethodSource("provideValidateValidNotBlank")
    public void testValidateValidNotBlank(Object val) throws ValidationException {
        NotBlank.notBlank().validate(val);
        // No exception should be thrown
    }

    public static Stream<Object> provideValidateValidNotBlank() {
        return Stream.of("123", new Object(), 12);
    }

    public static Stream<Object> provideValidateInvalidNotBlank() {
        return Stream.of("");
    }

    @ParameterizedTest
    @MethodSource("provideValidateInvalidNotBlank")
    public void testValidateInValidNotBlank(Object val) {
        ValidationException e =
                assertThrows(ValidationException.class, () -> NotBlank.notBlank().validate(val));
        assertEquals(ValidationErrorCode.INVALID_FORMAT_VALUE, e.getValidationErrorCode());
    }

    public static Stream<Object> provideValidateInvalidNotBlankNull() {
        return Stream.of((Object) null);
    }

    @ParameterizedTest
    @MethodSource("provideValidateInvalidNotBlankNull")
    public void testValidateInValidNotBlankNull(Object val) {
        ValidationException e =
                assertThrows(ValidationException.class, () -> NotBlank.notBlank().validate(val));
        assertEquals(ValidationErrorCode.REQUIRED_DATA_ELEMENT_MISSING, e.getValidationErrorCode());
    }
}
