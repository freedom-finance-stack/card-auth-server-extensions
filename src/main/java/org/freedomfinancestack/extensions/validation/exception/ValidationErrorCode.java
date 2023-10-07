package org.freedomfinancestack.extensions.validation.exception;

import lombok.Getter;

@Getter
public enum ValidationErrorCode {
    DATA_NOT_FOUND("404", "DATA NOT FOUND"),
    INVALID_FORMAT("400", "Format of one or more elements is invalid"),
    INVALID_FORMAT_LENGTH("400", "Invalid Format - Length"),
    INVALID_FORMAT_VALUE("400", "Invalid Format - Value"),
    DUPLICATE_DATA_ELEMENT("400", "Duplicate Data Element"),
    DATA_DECRYPTION_FAILURE("400", "Data could not be decrypted"),
    ISO_CODE_INVALID("400", "ISO Code invalid"),
    REQUIRED_DATA_ELEMENT_MISSING("400", "A element required is missing from the request.");

    private final String errorCode;
    private final String defaultErrorMessage;

    ValidationErrorCode(String errorCode, String defaultErrorMessage) {
        this.errorCode = errorCode;
        this.defaultErrorMessage = defaultErrorMessage;
    }
}
