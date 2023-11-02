package org.freedomfinancestack.extensions.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("notification")
@Data
public class NotificationConfiguration {
    private SmsProperties sms;
    private EmailProperties email;

    @Getter
    @Setter
    public static class SmsProperties {
        private String enabledChannel;
    }

    @Getter
    @Setter
    public static class EmailProperties {
        private String enabledChannel;
        private SmtpProperties simpleSMTP;
    }

    @Getter
    @Setter
    public static class SmtpProperties {
        private String host;
        private int port;
    }
}
