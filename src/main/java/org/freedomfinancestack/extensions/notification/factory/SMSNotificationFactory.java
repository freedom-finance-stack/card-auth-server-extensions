package org.freedomfinancestack.extensions.notification.factory;

import org.freedomfinancestack.extensions.notification.NotificationConfiguration;
import org.freedomfinancestack.extensions.notification.SMSNotificationService;
import org.freedomfinancestack.extensions.notification.enums.SMSChannelType;
import org.freedomfinancestack.extensions.notification.exception.NotificationErrorCode;
import org.freedomfinancestack.extensions.notification.exception.NotificationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SMSNotificationFactory {
    @Qualifier("dummySMSServiceImpl") private final SMSNotificationService dummySMSService;

    private final NotificationConfiguration notificationConfig;

    public SMSNotificationService getSMSNotificationService() throws NotificationException {
        SMSNotificationService smsNotificationService = null;
        String smtpAPIType = notificationConfig.getSms().getEnabledChannel();
        SMSChannelType smsAPI = SMSChannelType.getSMSChannelType(smtpAPIType);
        if (smsAPI == null) {
            throw new NotificationException(
                    NotificationErrorCode.INVALID_CHANNEL_PROVIDED, "Invalid SMS Channel Type");
        }
        switch (smsAPI) {
            default:
                smsNotificationService = dummySMSService;
                break;
        }
        return smsNotificationService;
    }
}
