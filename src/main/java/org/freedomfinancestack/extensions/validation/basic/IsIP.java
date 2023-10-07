package org.freedomfinancestack.extensions.validation.basic;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.Validator;
import org.freedomfinancestack.extensions.validation.exception.ValidationErrorCode;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;

import com.google.common.net.InetAddresses;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsIP implements Validator<String> {
    private static final IsIP INSTANCE = new IsIP();

    public static IsIP isIP() {
        return INSTANCE;
    }

    @Override
    public void validate(String value) throws ValidationException {
        if (Util.isNullorBlank(value)) {
            return;
        }
        if (!InetAddresses.isInetAddress(value)) {
            throw new ValidationException(
                    ValidationErrorCode.INVALID_FORMAT_VALUE, "Invalid IP address");
        }
    }
}
