package org.freedomfinancestack.extensions.notification.impl;

import org.freedomfinancestack.extensions.notification.EmailNotificationService;
import org.freedomfinancestack.extensions.notification.dto.EmailNotificationDto;
import org.freedomfinancestack.extensions.notification.dto.NotificationResponseDto;
import org.freedomfinancestack.extensions.notification.enums.EmailChannelType;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DummyEmailServiceImpl implements EmailNotificationService {
    @Override
    public EmailChannelType getEmailChannelType() {
        return EmailChannelType.EMAIL_API_DUMMY;
    }

    @Override
    public NotificationResponseDto send(EmailNotificationDto notificationDto) {
        notificationDto
                .getTo()
                .forEach(
                        to ->
                                log.info(
                                        "Sending email to {} from {} with subject {} and template"
                                                + " {} with param {}",
                                        to,
                                        notificationDto.getFrom(),
                                        notificationDto.getSubject(),
                                        notificationDto.getTemplateName(),
                                        notificationDto.getTemplateData()));
        return NotificationResponseDto.success();
    }
}
