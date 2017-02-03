/* PerformanceLoggingAspect 1.0 01/29/2017 */
package com.softserve.edu.schedule.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Performance logging aspect for checking performance of the application
 * components.
 * 
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Component
@Aspect
public class PerformanceLoggingAspect {

    /**
     * Logger example to provide logging of performance.
     */
    private Logger log = LoggerFactory
            .getLogger(PerformanceLoggingAspect.class);

    @Around("@within(PerfomanceLoggable) || @annotation(PerfomanceLoggable)")
    public Object logPerfomanceMethod(ProceedingJoinPoint joinPoint)
            throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object retVal = joinPoint.proceed();
        stopWatch.stop();

        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }
        logMessage.append(")");
        logMessage.append(" execution time: ");
        logMessage.append(stopWatch.getTotalTimeMillis());
        logMessage.append(" ms");
        log.warn(logMessage.toString());
        return retVal;
    }
}
