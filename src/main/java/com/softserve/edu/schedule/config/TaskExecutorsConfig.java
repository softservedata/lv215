/* TaskExecutorsConfig 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.softserve.edu.schedule.exceptionhandlers.AsyncExceptionHandler;

/**
 * Configuration of async and scheduled tasks executors of application.
 *
 * @version 1.0 07 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Configuration
@EnableAsync
@EnableScheduling
public class TaskExecutorsConfig
        implements AsyncConfigurer, SchedulingConfigurer {

    /**
     * Async task executor core pool size.
     */
    private static final int ASYNC_EXECUTOR_CORE_POOL_SIZE = 10;

    /**
     * Async task executor max pool size.
     */
    private static final int ASYNC_EXECUTOR_MAX_POOL_SIZE = 20;

    /**
     * Async task executor queue capacity.
     */
    private static final int ASYNC_EXECUTOR_QUEUE_CAPACITY = 1000;

    /**
     * Scheduled task executor pool size.
     */
    private static final int SCHEDULED_EXECUTOR_POOL_SIZE = 10;

    /**
     * Provides the Executor instance to be used when processing async method
     * invocations.
     *
     * @return Executor instance
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ASYNC_EXECUTOR_CORE_POOL_SIZE);
        executor.setMaxPoolSize(ASYNC_EXECUTOR_MAX_POOL_SIZE);
        executor.setQueueCapacity(ASYNC_EXECUTOR_QUEUE_CAPACITY);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }

    /**
     * Provides the AsyncUncaughtExceptionHandler instance to be used when an
     * exception is thrown during an asynchronous method execution with void
     * return type.
     *
     * @return an AsyncExceptionHandler instance.
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    /**
     * Callback allowing a TaskScheduler and specific Task instances to be
     * registered against the given the ScheduledTaskRegistrar.
     *
     * @param taskRegistrar
     *            the registrar to be configured.
     */
    @Override
    public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(scheduledTaskExecutor());
    }

    /**
     * Set configured scheduled tasks executor for application.
     *
     * @return Executor instance
     */
    @Bean(destroyMethod = "shutdown")
    public Executor scheduledTaskExecutor() {
        return Executors.newScheduledThreadPool(SCHEDULED_EXECUTOR_POOL_SIZE);
    }

}
