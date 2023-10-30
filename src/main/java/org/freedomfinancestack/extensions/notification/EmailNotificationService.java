package org.freedomfinancestack.extensions.notification;

import org.freedomfinancestack.extensions.notification.dto.EmailNotificationDto;
import org.freedomfinancestack.extensions.notification.enums.EmailChannelType;

public interface EmailNotificationService {

    EmailChannelType getEmailChannelType();

    boolean send(EmailNotificationDto notificationDto);
}
