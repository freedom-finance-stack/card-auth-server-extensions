package org.freedomfinancestack.extensions.scheduledTask;

import java.util.concurrent.*;

import org.freedomfinancestack.extensions.scheduledTask.config.TaskSchedulerConfig;
import org.freedomfinancestack.extensions.scheduledTask.exception.TaskAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedulerWrapper {
    private final ScheduledExecutorService executorService;
    private final ConcurrentHashMap<String, ScheduledFuture<?>> taskMap;
    private final TaskSchedulerConfig taskSchedulerConfig;

    @Autowired
    public TaskSchedulerWrapper(TaskSchedulerConfig taskSchedulerConfig) {
        this.executorService =
                createCustomScheduledExecutor(
                        taskSchedulerConfig.getCorePoolSize(),
                        taskSchedulerConfig.getMaxPoolSize(),
                        taskSchedulerConfig.getKeepAliveTime(),
                        TimeUnit.MILLISECONDS);
        this.taskMap = new ConcurrentHashMap<>();
        this.taskSchedulerConfig = taskSchedulerConfig;
    }

    public ScheduledFuture<?> scheduleTask(String taskId, Runnable task, long delay, TimeUnit unit)
            throws TaskAlreadyExistException {
        if (taskMap.containsKey(taskId)) {
            throw new TaskAlreadyExistException("Task with id " + taskId + " is already scheduled");
        }
        ScheduledFuture<?> future = executorService.schedule(task, delay, unit);
        taskMap.put(taskId, future);
        return future;
    }

    private static ScheduledExecutorService createCustomScheduledExecutor(
            int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(corePoolSize);
        executor.setMaximumPoolSize(maxPoolSize);
        executor.setKeepAliveTime(keepAliveTime, unit);
        return Executors.unconfigurableScheduledExecutorService(executor);
    }

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
