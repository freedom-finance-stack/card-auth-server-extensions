package org.freedomfinancestack.extensions.validation.exception;

import lombok.Getter;

@Getter
public enum ValidationErrorCode {
    INVALID_FORMAT("203", "Format of one or more elements is invalid"),
    INVALID_FORMAT_LENGTH("203", "Invalid Format - Length"),
    INVALID_FORMAT_VALUE("203", "Invalid Format - Value"),
    REQUIRED_DATA_ELEMENT_MISSING("201", "A element required is missing from the request.");

    private final String errorCode;
    private final String defaultErrorMessage;

    ValidationErrorCode(String errorCode, String defaultErrorMessage) {
        this.errorCode = errorCode;
        this.defaultErrorMessage = defaultErrorMessage;
    }
}
