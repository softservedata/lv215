/* AsyncExceptionHandler 1.0 02/10/2017 */
package com.softserve.edu.schedule.exceptionhandlers;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * A class for handling uncaught exceptions thrown from asynchronous methods.
 *
 * @version 1.0 10 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    /**
     * Logger example to provide logging of exceptions.
     */
    private Logger log = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);

    /**
     * Handle the given uncaught exception thrown from an asynchronous method.
     *
     * @param ex
     *            the exception thrown from the asynchronous method
     *
     * @param method
     *            the asynchronous method
     *
     * @param params
     *            the parameters used to invoked the method
     */
    @Override
    public void handleUncaughtException(final Throwable ex, final Method method,
            final Object... params) {
        log.error(ex.getMessage(), ex);
    }
}
