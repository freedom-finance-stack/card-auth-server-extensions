package org.freedomfinancestack.extensions.hsm.exception;

import lombok.Getter;

@Getter
public enum HSMErrorCode {

    /** HSM Validation Exceptions */
    KCV_EMPTY_IN_HSM_MESSAGE("1001", "KCV is either blank or null in HSM Message Data"),
    DATA_EMPTY_IN_HSM_MESSAGE("1002", "Data is either blank or null in HSM Message Data"),

    /** CVV Exceptions */
    CVV_GENERATED_BLANK("2001", "HSM Response cannot be null or empty"),
    CVV_GENERATED_INCORRECT("2002", "HSM Response cannot be less than 3 digits"),

    /** HSM Exceptions */
    HSM_SEND_REQUEST_ERROR("3001", "Exception occurred while sending request to HSM"),
    HSM_FETCH_RESPONSE_ERROR("3002", "Exception occurred while fetching response from HSM");

    private final String code;
    private final String defaultErrorMessage;

    HSMErrorCode(String code, String defaultErrorMessage) {
        this.code = code;
        this.defaultErrorMessage = defaultErrorMessage;
    }
}
