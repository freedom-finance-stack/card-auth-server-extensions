package org.freedomfinancestack.extensions.notification.enums;

import lombok.Getter;

public enum EmailChannelType {
    SMTP_API_SERVER("smtp-server"),
    SMTP_API_DUMMY("dummy-smtp-server");

    @Getter private final String apiName;

    EmailChannelType(String apiName) {
        this.apiName = apiName;
    }

    public static EmailChannelType getEmailChannelType(String emailChannelType) {
        for (EmailChannelType smtpAPI : EmailChannelType.values()) {
            if (smtpAPI.getApiName().equals(emailChannelType)) {
                return smtpAPI;
            }
        }
        return null;
    }
}
