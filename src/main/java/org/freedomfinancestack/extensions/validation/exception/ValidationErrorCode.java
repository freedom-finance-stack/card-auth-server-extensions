package org.freedomfinancestack.extensions.validation.exception;

import lombok.Getter;

@Getter
public enum ValidationErrorCode {
    INVALID_FORMAT("1000", "Format of one or more elements is invalid"),
    INVALID_FORMAT_LENGTH("1001", "Invalid Format - Length"),
    INVALID_FORMAT_VALUE("1002", "Invalid Format - Value"),
    REQUIRED_DATA_ELEMENT_MISSING("1003", "A element required is missing from the request."),
    NOT_EQUAL("1004", "A element is equal to the given value.");

    private final String errorCode;
    private final String defaultErrorMessage;

    ValidationErrorCode(String errorCode, String defaultErrorMessage) {
        this.errorCode = errorCode;
        this.defaultErrorMessage = defaultErrorMessage;
    }
}
