package org.freedomfinancestack.extensions.notification.enums;

import lombok.Getter;

public enum SMSChannelType {
    SMS_API_DUMMY("dummy-sms-server");

    @Getter private final String apiName;

    SMSChannelType(String apiName) {
        this.apiName = apiName;
    }

    public static SMSChannelType getSMSChannelType(String emailChannelType) {
        for (SMSChannelType smtpAPI : SMSChannelType.values()) {
            if (smtpAPI.getApiName().equals(emailChannelType)) {
                return smtpAPI;
            }
        }
        return null;
    }
}
