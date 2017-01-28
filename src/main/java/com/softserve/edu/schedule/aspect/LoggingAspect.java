package com.softserve.edu.schedule.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAspect {

    private Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    
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

    @AfterThrowing(pointcut = "@within(Loggable) || @annotation(Loggable)",
            throwing = "e")
    public void logException(JoinPoint joinPoint, Throwable e) {
        String methodFullName = joinPoint.getSignature().toString();
        log.error("We have caught exception in method: " + methodFullName
                + " the exception is: " + e.getMessage(), e);
    }
}
