package org.freedomfinancestack.extensions.scheduledTask.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class for the task scheduler.
 *
 * @author jaydeepRadadiya
 * @since 1.0.4
 * @version 1.0.4
 */
@Configuration
@ConfigurationProperties(prefix = "task.scheduler")
@Getter
@Setter
public class TaskSchedulerConfig {
    // todo consumer wants to create multiple tasks schedulers , how do we give different configs as
    // the properties is defined as task.scheduler
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
}
