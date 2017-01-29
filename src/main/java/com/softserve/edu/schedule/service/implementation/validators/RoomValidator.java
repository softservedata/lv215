/* RoomValidator 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The validator of checking room input data.
 * 
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = RoomValidatorImpl.class)
public @interface RoomValidator {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * An input parameter room name
     */
    String name();

    /**
     * An input parameter room capacity
     * 
     */
    String capacity();

}
