/* PerfomanceLoggable 1.0 01/29/2017 */
package com.softserve.edu.schedule.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Performance logging annotation for checking performance of the application
 * components.
 * 
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PerfomanceLoggable {

}
