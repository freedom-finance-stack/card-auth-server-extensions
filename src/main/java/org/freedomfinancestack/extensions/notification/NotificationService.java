package org.freedomfinancestack.extensions.notification;

import lombok.RequiredArgsConstructor;
import org.freedomfinancestack.extensions.notification.dto.EmailNotificationDto;
import org.freedomfinancestack.extensions.notification.dto.NotificationDto;
import org.freedomfinancestack.extensions.notification.dto.SMSNotificationDto;
import org.freedomfinancestack.extensions.notification.enums.NotificationChannelType;
import org.freedomfinancestack.extensions.notification.factory.EmailNotificationFactory;
import org.freedomfinancestack.extensions.notification.factory.SMSNotificationFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmailNotificationFactory emailNotificationFactory;
    private final SMSNotificationFactory smsNotificationFactory;

    public boolean send(NotificationChannelType channelType, NotificationDto notificationDto) {
        switch (channelType) {
            case EMAIL:
                return emailNotificationFactory
                        .getEmailNotificationService()
                        .send((EmailNotificationDto) notificationDto);
            case SMS:
                return smsNotificationFactory
                        .getSMSNotificationService()
                        .send((SMSNotificationDto) notificationDto);
        }
        return false;
    }
}
