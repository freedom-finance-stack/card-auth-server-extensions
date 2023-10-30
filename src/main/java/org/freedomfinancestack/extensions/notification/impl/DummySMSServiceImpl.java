package org.freedomfinancestack.extensions.notification.impl;

import lombok.extern.slf4j.Slf4j;
import org.freedomfinancestack.extensions.notification.SMSNotificationService;
import org.freedomfinancestack.extensions.notification.dto.SMSNotificationDto;
import org.freedomfinancestack.extensions.notification.enums.SMSChannelType;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummySMSServiceImpl implements SMSNotificationService {

    @Override
    public SMSChannelType getSMSAPIType() {
        return SMSChannelType.SMS_API_DUMMY;
    }

    @Override
    public boolean send(SMSNotificationDto smsNotificationDto) {
        smsNotificationDto
                .getTo()
                .forEach(
                        to -> {
                            log.info(
                                    "Sending SMS to {} with message {}",
                                    to,
                                    smsNotificationDto.getMessage());
                        });
        return true;
    }
}
