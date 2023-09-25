package org.freedomfinancestack.extensions.timer;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.freedomfinancestack.extensions.scheduledTask.exception.TaskAlreadyExistException;

/**
 * An interface for scheduling and managing timeout tasks.
 *
 * @author jaydeepRadadiya
 * @version 1.0.4
 * @since 1.0.4
 */
public interface TimerService {

    /**
     * Schedule a timeout task with a unique ID. If task Already exist than it will cancel previous
     * task and create new one with new delay.
     *
     * @param id The unique ID for the task.
     * @param task The Runnable task to be executed.
     * @param delay The delay in time units before the task is executed.
     * @param unit The TimeUnit for the delay.
     * @return A ScheduledFuture representing the scheduled task.
     * @throws TaskAlreadyExistException If a task with the same ID already exists.
     */
    ScheduledFuture<?> scheduleTimeoutTask(String id, Runnable task, long delay, TimeUnit unit)
            throws TaskAlreadyExistException;

    /**
     * Remove a timeout task with the given ID.
     *
     * @param id The unique ID of the task to be removed.
     * @return True if the task was successfully removed, false otherwise.
     */
    boolean removeTimeoutTask(String id);
}
