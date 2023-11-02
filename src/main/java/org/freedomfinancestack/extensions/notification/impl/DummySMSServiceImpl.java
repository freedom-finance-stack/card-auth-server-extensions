package org.freedomfinancestack.extensions.notification.impl;

import org.freedomfinancestack.extensions.notification.SMSNotificationService;
import org.freedomfinancestack.extensions.notification.dto.NotificationResponseDto;
import org.freedomfinancestack.extensions.notification.dto.SMSNotificationDto;
import org.freedomfinancestack.extensions.notification.enums.SMSChannelType;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DummySMSServiceImpl implements SMSNotificationService {

    @Override
    public SMSChannelType getSMSAPIType() {
        return SMSChannelType.SMS_API_DUMMY;
    }

    @Override
    public NotificationResponseDto send(SMSNotificationDto smsNotificationDto) {
        smsNotificationDto
                .getTo()
                .forEach(
                        to -> {
                            log.info(
                                    "Sending SMS to {} with template name {} and template data {}",
                                    to,
                                    smsNotificationDto.getTemplateName(),
                                    smsNotificationDto.getTemplateData());
                        });
        return NotificationResponseDto.success();
    }
}
