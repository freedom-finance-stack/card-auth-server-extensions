package org.freedomfinancestack.extensions.validation.validator.basic;

import java.util.stream.Stream;

import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotNullTest {

    @ParameterizedTest
    @MethodSource("provideValidateValidNotNull")
    public void testValidateValidNotNull(Object val) throws ValidationException {
        NotNull.notNull().validate(val);
        // No exception should be thrown
    }

    public static Stream<Object> provideValidateValidNotNull() {
        return Stream.of("123", new Object(), new Integer(12));
    }

    public static Stream<Object> provideValidateInvalidNotNull() {
        return Stream.of(null, "", new String());
    }

    @ParameterizedTest
    @MethodSource("provideValidateInvalidNotNull")
    public void testValidateInValidNotNull(Object val) {
        ValidationException e =
                assertThrows(ValidationException.class, () -> NotNull.notNull().validate(val));
        assertEquals(ValidationErrorCode.REQUIRED_DATA_ELEMENT_MISSING, e.getValidationErrorCode());
    }
}
