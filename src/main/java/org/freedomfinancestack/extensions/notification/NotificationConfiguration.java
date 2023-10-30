package org.freedomfinancestack.extensions.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;

@Configuration
@ConfigurationProperties("notification")
@Data
public class NotificationConfiguration {
    private SmsProperties sms;
    private EmailProperties email;

    @Getter
    public static class SmsProperties {
        private String enabledChannel;
    }

    @Getter
    public static class EmailProperties {
        private String enabledChannel;
        private SmtpProperties simpleSMTP;
    }

    @Getter
    public static class SmtpProperties {
        private String host;
        private int port;
    }
}
