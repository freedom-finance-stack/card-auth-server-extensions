package org.freedomfinancestack.extensions.notification.factory;

import org.freedomfinancestack.extensions.notification.EmailNotificationService;
import org.freedomfinancestack.extensions.notification.NotificationConfiguration;
import org.freedomfinancestack.extensions.notification.enums.EmailChannelType;
import org.freedomfinancestack.extensions.notification.exception.NotificationErrorCode;
import org.freedomfinancestack.extensions.notification.exception.NotificationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailNotificationFactory {
    @Qualifier("smtpServerServiceImpl") private EmailNotificationService smtpServerNotificationService;

    @Qualifier("dummyEmailServiceImpl") private EmailNotificationService dummySMTPNotificationService;

    private NotificationConfiguration notificationConfig;

    public EmailNotificationService getEmailNotificationService() throws NotificationException {
        EmailNotificationService notificationService = null;
        String emailAPIType = notificationConfig.getEmail().getEnabledChannel();
        EmailChannelType emailAPI = EmailChannelType.getEmailChannelType(emailAPIType);
        if (emailAPI == null) {
            throw new NotificationException(
                    NotificationErrorCode.INVALID_CHANNEL_PROVIDED, "Invalid EMAIL Channel Type");
        }
        switch (emailAPI) {
            case SMTP_API_SERVER:
                notificationService = smtpServerNotificationService;
                break;
            default:
                notificationService = dummySMTPNotificationService;
                break;
        }
        return notificationService;
    }
}
