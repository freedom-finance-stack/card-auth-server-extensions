package org.freedomfinancestack.extensions.timer;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.freedomfinancestack.extensions.scheduledTask.exception.TaskAlreadyExistException;

public interface TimerService {
    ScheduledFuture<?> scheduleTimeoutTask(String id, Runnable task, long delay, TimeUnit unit)
            throws TaskAlreadyExistException;

    boolean removeTimeoutTask(String id);
}
