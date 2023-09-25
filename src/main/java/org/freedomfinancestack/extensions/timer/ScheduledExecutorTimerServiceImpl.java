package org.freedomfinancestack.extensions.timer;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.freedomfinancestack.extensions.scheduledTask.TaskSchedulerWrapper;
import org.freedomfinancestack.extensions.scheduledTask.exception.TaskAlreadyExistException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * An implementation of the TimerService interface using a ScheduledExecutorService. Provides
 * methods for scheduling and canceling timeout tasks. @Author jaydeepRadadiya
 *
 * @since 1.0.4 @Version 1.0.4
 */
@Service("ScheduledExecutorTimerServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class ScheduledExecutorTimerServiceImpl implements TimerService {

    private final TaskSchedulerWrapper taskSchedulerWrapper;

    @Override
    public ScheduledFuture<?> scheduleTimeoutTask(
            String id, Runnable task, long delay, TimeUnit unit) throws TaskAlreadyExistException {
        // Check if a task with the same ID exists
        boolean taskExists = taskSchedulerWrapper.cancelTask(id);

        if (taskExists) {
            log.info("Task with ID {} already exists, cancelling and rescheduling.", id);
            return taskSchedulerWrapper.scheduleTask(id, task, delay, unit);
        } else {
            log.info("Task with ID {} does not exist, scheduling new task.", id);
            // Task with the same ID did not exist, schedule the new task.
            return taskSchedulerWrapper.scheduleTask(id, task, delay, unit);
        }
    }

    @Override
    public boolean removeTimeoutTask(String id) {
        return taskSchedulerWrapper.cancelTask(id);
    }
}
