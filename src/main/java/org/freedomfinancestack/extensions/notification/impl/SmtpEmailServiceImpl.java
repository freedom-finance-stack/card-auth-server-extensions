package org.freedomfinancestack.extensions.notification.impl;

import org.freedomfinancestack.extensions.notification.EmailNotificationService;
import org.freedomfinancestack.extensions.notification.dto.EmailNotificationDto;
import org.freedomfinancestack.extensions.notification.dto.NotificationResponseDto;
import org.freedomfinancestack.extensions.notification.enums.EmailChannelType;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmtpEmailServiceImpl implements EmailNotificationService {
    @Override
    public EmailChannelType getEmailChannelType() {
        return EmailChannelType.SMTP_API_SERVER;
    }

    @Override
    public NotificationResponseDto send(EmailNotificationDto notificationDto) {
        // todo implement this
        return NotificationResponseDto.success();
    }
}
