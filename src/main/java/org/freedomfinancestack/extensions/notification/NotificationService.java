package org.freedomfinancestack.extensions.notification;

import org.freedomfinancestack.extensions.notification.dto.EmailNotificationDto;
import org.freedomfinancestack.extensions.notification.dto.NotificationDto;
import org.freedomfinancestack.extensions.notification.dto.NotificationResponseDto;
import org.freedomfinancestack.extensions.notification.dto.SMSNotificationDto;
import org.freedomfinancestack.extensions.notification.enums.NotificationChannelType;
import org.freedomfinancestack.extensions.notification.exception.NotificationErrorCode;
import org.freedomfinancestack.extensions.notification.exception.NotificationException;
import org.freedomfinancestack.extensions.notification.factory.EmailNotificationFactory;
import org.freedomfinancestack.extensions.notification.factory.SMSNotificationFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmailNotificationFactory emailNotificationFactory;
    private final SMSNotificationFactory smsNotificationFactory;

    public NotificationResponseDto send(
            NotificationChannelType channelType, NotificationDto notificationDto)
            throws NotificationException {
        if (notificationDto == null) {
            throw new NotificationException(
                    NotificationErrorCode.INVALID_NOTIFICATION_DTO, "Invalid Notification Dto");
        }
        if (channelType == null) {
            throw new NotificationException(
                    NotificationErrorCode.INVALID_CHANNEL_PROVIDED,
                    "Empty Notification Channel Type");
        }
        switch (channelType) {
            case EMAIL -> {
                if (notificationDto instanceof EmailNotificationDto) {
                    return emailNotificationFactory
                            .getEmailNotificationService()
                            .send((EmailNotificationDto) notificationDto);
                } else {
                    throw new NotificationException(
                            NotificationErrorCode.INVALID_NOTIFICATION_DTO,
                            "Invalid notification dto type for EMAIL channel.");
                }
            }
            case SMS -> {
                if (notificationDto instanceof SMSNotificationDto) {
                    return smsNotificationFactory
                            .getSMSNotificationService()
                            .send((SMSNotificationDto) notificationDto);
                } else {
                    throw new NotificationException(
                            NotificationErrorCode.INVALID_NOTIFICATION_DTO,
                            "Invalid notification dto type for SMS channel.");
                }
            }
        }
        return NotificationResponseDto.failure();
    }
}
