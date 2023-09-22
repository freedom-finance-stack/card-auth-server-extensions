package org.freedomfinancestack.extensions.scheduledTask.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "task.scheduler")
@Getter
@Setter
public class TaskSchedulerConfig {
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
}
