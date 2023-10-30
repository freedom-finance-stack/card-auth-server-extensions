package org.freedomfinancestack.extensions.notification.factory;

import org.freedomfinancestack.extensions.notification.EmailNotificationService;
import org.freedomfinancestack.extensions.notification.NotificationConfiguration;
import org.freedomfinancestack.extensions.notification.enums.EmailChannelType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailNotificationFactory {
    @Qualifier("smtpServerServiceImpl") private EmailNotificationService smtpServerNotificationService;

    @Qualifier("dummyEmailServiceImpl") private EmailNotificationService dummySMTPNotificationService;

    private NotificationConfiguration notificationConfig;

    public EmailNotificationService getEmailNotificationService() {
        EmailNotificationService smtpNotificationService = null;
        String smtpAPIType = notificationConfig.getEmail().getEnabledChannel();
        EmailChannelType smtpAPI = EmailChannelType.getEmailChannelType(smtpAPIType);

        switch (smtpAPI) {
            case SMTP_API_SERVER:
                smtpNotificationService = smtpServerNotificationService;
                break;
            default:
                smtpNotificationService = dummySMTPNotificationService;
                break;
        }
        return smtpNotificationService;
    }
}
