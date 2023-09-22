package org.freedomfinancestack.extensions.timer;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.freedomfinancestack.extensions.scheduledTask.TaskSchedulerWrapper;
import org.freedomfinancestack.extensions.scheduledTask.exception.TaskAlreadyExistException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("ScheduledExecutorTimerServiceImpl")
@RequiredArgsConstructor
public class ScheduledExecutorTimerServiceImpl implements TimerService {

    private final TaskSchedulerWrapper taskSchedulerWrapper;

    @Override
    public ScheduledFuture<?> scheduleTimeoutTask(
            String id, Runnable task, long delay, TimeUnit unit) throws TaskAlreadyExistException {
        return taskSchedulerWrapper.scheduleTask(id, task, delay, unit);
    }

    @Override
    public boolean removeTimeoutTask(String id) {
        return taskSchedulerWrapper.cancelTask(id);
    }
}
