package org.freedomfinancestack.extensions.notification.factory;

import lombok.extern.slf4j.Slf4j;
import org.freedomfinancestack.extensions.notification.NotificationConfiguration;
import org.freedomfinancestack.extensions.notification.SMSNotificationService;
import org.freedomfinancestack.extensions.notification.enums.SMSChannelType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SMSNotificationFactory {
    @Qualifier("dummySMSServiceImpl") private SMSNotificationService dummySMSService;

    private NotificationConfiguration notificationConfig;

    public SMSNotificationService getSMSNotificationService() {
        SMSNotificationService smsNotificationService = null;
        String smtpAPIType = notificationConfig.getSms().getEnabledChannel();
        SMSChannelType smsAPI = SMSChannelType.getSMSChannelType(smtpAPIType);
        switch (smsAPI) {
            default:
                smsNotificationService = dummySMSService;
                break;
        }
        return smsNotificationService;
    }
}
