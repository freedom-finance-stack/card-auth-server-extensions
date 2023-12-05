package org.freedomfinancestack.extensions.validation.validator.enriched;

import org.freedomfinancestack.extensions.validation.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsEqualTest {

    @Test
    void testEqualValues() throws ValidationException {
        String expectedValue = "apple";
        IsEqual<String> validator = IsEqual.isEqual(expectedValue);

        // Should not throw an exception for equal values
        validator.validate("apple");

        // Should throw an exception for non-equal values
        assertThrows(ValidationException.class, () -> validator.validate("orange"));
    }

    @Test
    void testNullValue() throws ValidationException {
        String expectedValue = "apple";
        IsEqual<String> validator = IsEqual.isEqual(expectedValue);

        // Null value should not throw an exception
        validator.validate(null);
    }

    @Test
    void testBlankValue() throws ValidationException {
        String expectedValue = "apple";
        IsEqual<String> validator = IsEqual.isEqual(expectedValue);

        // Blank value should not throw an exception
        validator.validate("");
    }

    @Test
    void testPrimitiveValue() throws ValidationException {
        int expectedValue = 3;
        IsEqual<Integer> validator = IsEqual.isEqual(expectedValue);
        validator.validate(3);
    }
}
