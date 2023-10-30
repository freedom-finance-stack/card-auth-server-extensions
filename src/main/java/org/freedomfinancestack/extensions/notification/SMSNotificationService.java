package org.freedomfinancestack.extensions.notification;

import org.freedomfinancestack.extensions.notification.dto.SMSNotificationDto;
import org.freedomfinancestack.extensions.notification.enums.SMSChannelType;

public interface SMSNotificationService {

    SMSChannelType getSMSAPIType();

    boolean send(SMSNotificationDto smsNotificationDto);
}
