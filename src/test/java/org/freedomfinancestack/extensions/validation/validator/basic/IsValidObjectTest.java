package org.freedomfinancestack.extensions.validation.validator.basic;

import java.util.stream.Stream;

import org.freedomfinancestack.extensions.validation.Validatable;
import org.freedomfinancestack.extensions.validation.basic.IsValidObject;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsValidObjectTest {

    private static Stream<Validatable> validatableProvider() {
        // Define valid and invalid Validatable objects for testing
        return Stream.of(() -> true, null);
    }

    private static Stream<Validatable> inValidValidatableProvider() {
        // Define valid and invalid Validatable objects for testing
        return Stream.of(() -> false);
    }

    @ParameterizedTest
    @DisplayName("Validate Validatable objects")
    @MethodSource("validatableProvider")
    void testIsValidObject(Validatable validatable) throws ValidationException {
        // No exception should be thrown for valid Validatable objects
        IsValidObject.isValidObject().validate(validatable);
    }

    @ParameterizedTest
    @DisplayName("Validate invalid Validatable objects")
    @MethodSource("inValidValidatableProvider")
    void testInvalidValidatableObjects(Validatable validatable) {
        ValidationException exception =
                assertThrows(
                        ValidationException.class,
                        () -> IsValidObject.isValidObject().validate(validatable));
        assertEquals(ValidationErrorCode.INVALID_FORMAT_VALUE, exception.getValidationErrorCode());
    }
}
