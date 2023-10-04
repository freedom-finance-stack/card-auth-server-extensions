package org.freedomfinancestack.extensions.scheduledTask;

import java.util.concurrent.*;

import org.freedomfinancestack.extensions.scheduledTask.config.TaskSchedulerConfig;
import org.freedomfinancestack.extensions.scheduledTask.exception.TaskAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * A wrapper class for managing scheduled tasks using a custom ScheduledExecutorService. Provides
 * methods for scheduling and canceling tasks with unique IDs.
 *
 * @author jaydeepRadadiya
 * @since 1.0.4
 * @version 1.0.4
 */
@Component
@Slf4j
public class TaskSchedulerWrapper {
    private final ScheduledExecutorService executorService;
    private final ConcurrentHashMap<String, ScheduledFuture<?>> taskMap;

    @Autowired
    public TaskSchedulerWrapper(TaskSchedulerConfig taskSchedulerConfig) {
        this.executorService =
                createCustomScheduledExecutor(
                        taskSchedulerConfig.getCorePoolSize(),
                        taskSchedulerConfig.getMaxPoolSize(),
                        taskSchedulerConfig.getKeepAliveTime(),
                        TimeUnit.MILLISECONDS);
        this.taskMap = new ConcurrentHashMap<>();
    }

    /**
     * Schedule a task with a unique ID.
     *
     * @param taskId
     * @param task
     * @param delay
     * @param unit
     * @return ScheduledFuture
     * @throws TaskAlreadyExistException
     */
    public ScheduledFuture<?> scheduleTask(String taskId, Runnable task, long delay, TimeUnit unit)
            throws TaskAlreadyExistException {
        if (taskMap.containsKey(taskId)) {
            throw new TaskAlreadyExistException("Task with id " + taskId + " is already scheduled");
        }
        ScheduledFuture<?> future = executorService.schedule(task, delay, unit);
        taskMap.put(taskId, future);
        return future;
    }

    /**
     * Creates a custom ScheduledExecutorService with the given parameters.
     *
     * @param corePoolSize
     * @param maxPoolSize
     * @param keepAliveTime
     * @param unit
     * @return
     */
    private static ScheduledExecutorService createCustomScheduledExecutor(
            int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(corePoolSize);
        executor.setMaximumPoolSize(maxPoolSize);
        executor.setKeepAliveTime(keepAliveTime, unit);
        return Executors.unconfigurableScheduledExecutorService(executor);
    }

    /**
     * Cancel a task with the given ID.
     *
     * @param taskId
     * @return
     */
    public boolean cancelTask(String taskId) {
        if (taskMap.containsKey(taskId)) {
            ScheduledFuture<?> future = taskMap.get(taskId);
            if (future != null) {
                future.cancel(true);
                taskMap.remove(taskId);
                return true;
            }
        }
        return false;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
